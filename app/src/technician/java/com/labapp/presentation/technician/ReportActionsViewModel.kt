package com.labapp.presentation.technician

import android.content.ActivityNotFoundException
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.CancellationSignal
import android.os.Environment
import android.os.ParcelFileDescriptor
import android.print.PageRange
import android.print.PrintAttributes
import android.print.PrintDocumentAdapter
import android.print.PrintDocumentInfo
import android.print.PrintManager
import android.provider.MediaStore
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.labapp.data.model.ReportDoc
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.concurrent.TimeUnit
import javax.inject.Inject

sealed interface ReportActionState {
    data object Idle : ReportActionState
    data class InProgress(val message: String) : ReportActionState
    data class Success(val message: String) : ReportActionState
    data class Error(val message: String) : ReportActionState
}

@HiltViewModel
class ReportActionsViewModel @Inject constructor(
    @ApplicationContext private val appContext: Context
) : ViewModel() {

    private val _actionState = MutableStateFlow<ReportActionState>(ReportActionState.Idle)
    val actionState: StateFlow<ReportActionState> = _actionState.asStateFlow()

    private val httpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .build()

    fun consumeState() {
        _actionState.value = ReportActionState.Idle
    }

    fun downloadToStorage(report: ReportDoc) {
        if (!validatePdfReady(report)) return
        _actionState.value = ReportActionState.InProgress("Downloading report...")
        viewModelScope.launch {
            runCatching {
                val cached = fetchPdfToCache(report)
                saveToDownloads(cached, report)
            }.onSuccess { fileName ->
                _actionState.value =
                    ReportActionState.Success("Saved to Downloads/$fileName")
            }.onFailure { e ->
                _actionState.value =
                    ReportActionState.Error(e.message ?: "Download failed")
            }
        }
    }

    fun printReport(report: ReportDoc) {
        if (!validatePdfReady(report)) return
        _actionState.value = ReportActionState.InProgress("Preparing print...")
        viewModelScope.launch {
            runCatching { fetchPdfToCache(report) }
                .onSuccess { file ->
                    _actionState.value = ReportActionState.Idle
                    launchSystemPrint(file, report)
                }
                .onFailure { e ->
                    _actionState.value =
                        ReportActionState.Error(e.message ?: "Print failed")
                }
        }
    }

    fun shareViaWhatsApp(report: ReportDoc) {
        if (!validatePdfReady(report)) return
        _actionState.value = ReportActionState.InProgress("Opening WhatsApp...")
        viewModelScope.launch {
            runCatching { fetchPdfToCache(report) }
                .onSuccess { file ->
                    _actionState.value = ReportActionState.Idle
                    launchWhatsAppShare(file, report)
                }
                .onFailure { e ->
                    _actionState.value =
                        ReportActionState.Error(e.message ?: "Share failed")
                }
        }
    }

    private fun validatePdfReady(report: ReportDoc): Boolean {
        if (report.pdfUrl.isNullOrBlank()) {
            _actionState.value = ReportActionState.Error(
                "PDF is still being generated. Please try again shortly."
            )
            return false
        }
        return true
    }

    private suspend fun fetchPdfToCache(report: ReportDoc): File =
        withContext(Dispatchers.IO) {
            val url = requireNotNull(report.pdfUrl)
            val request = Request.Builder().url(url).build()
            httpClient.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    throw IllegalStateException("Could not fetch PDF (HTTP ${response.code})")
                }
                val dir = File(appContext.cacheDir, CACHE_DIR).apply { mkdirs() }
                val outFile = File(dir, buildFileName(report))
                val body = response.body
                    ?: throw IllegalStateException("Empty PDF response from server")
                body.byteStream().use { input ->
                    FileOutputStream(outFile).use { output -> input.copyTo(output) }
                }
                outFile
            }
        }

    private suspend fun saveToDownloads(source: File, report: ReportDoc): String =
        withContext(Dispatchers.IO) {
            val fileName = buildFileName(report)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val resolver = appContext.contentResolver
                val values = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                    put(MediaStore.MediaColumns.MIME_TYPE, MIME_PDF)
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS)
                }
                val uri = resolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, values)
                    ?: throw IllegalStateException("Could not create entry in Downloads")
                resolver.openOutputStream(uri)?.use { output ->
                    source.inputStream().use { input -> input.copyTo(output) }
                } ?: throw IllegalStateException("Could not write to Downloads")
            } else {
                @Suppress("DEPRECATION")
                val downloadsDir = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS
                )
                downloadsDir.mkdirs()
                val dest = File(downloadsDir, fileName)
                source.inputStream().use { input ->
                    FileOutputStream(dest).use { output -> input.copyTo(output) }
                }
                MediaScannerConnection.scanFile(
                    appContext,
                    arrayOf(dest.absolutePath),
                    arrayOf(MIME_PDF),
                    null
                )
            }
            fileName
        }

    private fun launchSystemPrint(file: File, report: ReportDoc) {
        val printManager =
            appContext.getSystemService(Context.PRINT_SERVICE) as PrintManager
        val jobName = buildFileName(report)
        val attributes = PrintAttributes.Builder()
            .setMediaSize(PrintAttributes.MediaSize.ISO_A4)
            .setColorMode(PrintAttributes.COLOR_MODE_COLOR)
            .setMinMargins(PrintAttributes.Margins.NO_MARGINS)
            .build()
        printManager.print(jobName, CachedPdfPrintAdapter(file, jobName), attributes)
    }

    private fun launchWhatsAppShare(file: File, report: ReportDoc) {
        val uri = FileProvider.getUriForFile(
            appContext,
            "${appContext.packageName}.fileprovider",
            file
        )
        val shareText = buildString {
            append("Diagnostic Test Report\n")
            append("Patient: ${report.patientSnapshot.name}\n")
            append("Test: ${report.testType}")
        }
        val sendIntent = Intent(Intent.ACTION_SEND).apply {
            type = MIME_PDF
            putExtra(Intent.EXTRA_STREAM, uri)
            putExtra(Intent.EXTRA_TEXT, shareText)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        for (pkg in WHATSAPP_PACKAGES) {
            sendIntent.setPackage(pkg)
            if (tryStartActivity(sendIntent)) return
        }
        sendIntent.setPackage(null)
        val chooser = Intent.createChooser(sendIntent, "Share report via")
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        tryStartActivity(chooser)
    }

    private fun tryStartActivity(intent: Intent): Boolean {
        return try {
            appContext.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
            true
        } catch (_: ActivityNotFoundException) {
            false
        }
    }

    private fun buildFileName(report: ReportDoc): String {
        val safeName = report.patientSnapshot.name
            .ifBlank { "Patient" }
            .replace(Regex("[^A-Za-z0-9]+"), "_")
            .trim('_')
            .take(24)
        val idSuffix = report.reportId.takeLast(6).ifBlank { "000000" }
        return "LabReport_${safeName}_$idSuffix.pdf"
    }

    private companion object {
        const val MIME_PDF = "application/pdf"
        const val CACHE_DIR = "reports"
        val WHATSAPP_PACKAGES = listOf("com.whatsapp", "com.whatsapp.w4b")
    }
}

class CachedPdfPrintAdapter(
    private val pdfFile: File,
    private val documentName: String
) : PrintDocumentAdapter() {

    override fun onLayout(
        oldAttributes: PrintAttributes?,
        newAttributes: PrintAttributes?,
        cancellationSignal: CancellationSignal?,
        callback: LayoutResultCallback?,
        extras: Bundle?
    ) {
        if (cancellationSignal?.isCanceled == true) {
            callback?.onLayoutCancelled()
            return
        }
        val info = PrintDocumentInfo.Builder(documentName)
            .setContentType(PrintDocumentInfo.CONTENT_TYPE_DOCUMENT)
            .build()
        callback?.onLayoutFinished(info, oldAttributes != newAttributes)
    }

    override fun onWrite(
        pages: Array<out PageRange>?,
        destination: ParcelFileDescriptor?,
        cancellationSignal: CancellationSignal?,
        callback: WriteResultCallback?
    ) {
        try {
            requireNotNull(destination) { "Print destination is null" }
            FileInputStream(pdfFile).use { input ->
                ParcelFileDescriptor.AutoCloseOutputStream(destination).use { output ->
                    input.copyTo(output)
                }
            }
            callback?.onWriteFinished(arrayOf(PageRange.ALL_PAGES))
        } catch (e: Exception) {
            callback?.onWriteFailed(e.message)
        }
    }
}
