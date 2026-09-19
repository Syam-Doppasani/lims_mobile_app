package com.labapp.presentation.technician

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Print
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.labapp.data.model.ValueFlag

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportDetailScreen(
    reportId: String,
    viewModel: ReportListViewModel,
    actionsViewModel: ReportActionsViewModel,
    onBackClick: () -> Unit,
    onShareClick: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val report = uiState.reports.find { it.reportId == reportId }
    val context = LocalContext.current
    val actionState by actionsViewModel.actionState.collectAsState()

    val storagePermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            report?.let { actionsViewModel.downloadToStorage(it) }
        } else {
            Toast.makeText(context, "Storage permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    fun startDownload(target: com.labapp.data.model.ReportDoc?) {
        if (target == null) return
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q &&
            ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED
        ) {
            storagePermissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        } else {
            actionsViewModel.downloadToStorage(target)
        }
    }

    LaunchedEffect(actionState) {
        when (val state = actionState) {
            is ReportActionState.Success -> {
                Toast.makeText(context, state.message, Toast.LENGTH_LONG).show()
                actionsViewModel.consumeState()
            }
            is ReportActionState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_LONG).show()
                actionsViewModel.consumeState()
            }
            else -> Unit
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Report Details") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    if (report?.pdfUrl != null) {
                        IconButton(onClick = { onShareClick(report.pdfUrl) }) {
                            Icon(Icons.Default.Share, contentDescription = "Share PDF Link")
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background)
            )
        },
        bottomBar = {
            if (report != null) {
                Surface(shadowElevation = 8.dp) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 10.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        OutlinedButton(
                            onClick = { actionsViewModel.printReport(report) },
                            enabled = actionState !is ReportActionState.InProgress,
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(Icons.Default.Print, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(Modifier.width(6.dp))
                            Text("Print")
                        }
                        OutlinedButton(
                            onClick = { startDownload(report) },
                            enabled = actionState !is ReportActionState.InProgress,
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(Icons.Default.Download, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(Modifier.width(6.dp))
                            Text("Download")
                        }
                        Button(
                            onClick = { actionsViewModel.shareViaWhatsApp(report) },
                            enabled = actionState !is ReportActionState.InProgress,
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(Icons.Default.Share, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(Modifier.width(6.dp))
                            Text("WhatsApp")
                        }
                    }
                }
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.background,
                            MaterialTheme.colorScheme.surface
                        )
                    )
                )
        ) {
            if (report == null) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Report not found.", color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                        ) {
                            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                Text("Patient Details", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, fontSize = 16.sp)
                                Text("Name: ${report.patientSnapshot.name}", fontWeight = FontWeight.Bold)
                                Text("Age/Sex: ${report.patientSnapshot.age} Yrs / ${report.patientSnapshot.sex}")
                                Text("Mobile: ${report.patientSnapshot.mobile}")
                                Divider(modifier = Modifier.padding(vertical = 4.dp))
                                Text("Test Code: ${report.testType}")
                                Text("Referral Doctor: " + (if (report.referralDoctorSnapshot.isSelf) "Self" else report.referralDoctorSnapshot.name))
                            }
                        }
                    }

                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                        ) {
                            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                Text("Delivery Details", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, fontSize = 16.sp)
                                Text("Status: ${report.status.uppercase()}")
                                report.whatsappSentAt?.let {
                                    Text("WhatsApp Sent: ${it.toDate().toString().take(16)}")
                                    Text("Recipient: ${report.whatsappRecipientMobile ?: ""}")
                                }
                                report.qrTokenExpiresAt?.let {
                                    Text("QR Valid Until: ${it.toDate().toString().take(16)}")
                                }
                            }
                        }
                    }

                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text("Test Parameters & Values", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, fontSize = 16.sp, modifier = Modifier.padding(bottom = 8.dp))
                                report.values.forEach { valObj ->
                                    val isAbnormal = valObj.flag == ValueFlag.HIGH || valObj.flag == ValueFlag.LOW
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 4.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(valObj.parameterName)
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Text(
                                                text = valObj.value + " " + valObj.unit,
                                                fontWeight = if (isAbnormal) FontWeight.Bold else FontWeight.Normal,
                                                color = if (isAbnormal) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface
                                            )
                                            if (isAbnormal) {
                                                Text(
                                                    text = if (valObj.flag == ValueFlag.HIGH) " ↑" else " ↓",
                                                    fontWeight = FontWeight.Bold,
                                                    color = MaterialTheme.colorScheme.error
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                if (actionState is ReportActionState.InProgress) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.scrim.copy(alpha = 0.35f)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)) {
                                Column(
                                    modifier = Modifier.padding(24.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    CircularProgressIndicator()
                                    Text((actionState as ReportActionState.InProgress).message)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
