package com.labapp.data.repository

import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.labapp.core.util.Result
import com.labapp.core.util.safeFirestoreCall
import com.labapp.data.model.ReportDoc
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReportRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : ReportRepository {

    override suspend fun createReport(report: ReportDoc): Result<String> {
        return safeFirestoreCall {
            firestore.collection("reports")
                .document(report.reportId)
                .set(report)
                .await()
            report.reportId
        }
    }

    override fun getReportsFlow(technicianId: String): Flow<Result<List<ReportDoc>>> = callbackFlow {
        val listener = firestore.collection("reports")
            .whereEqualTo("technicianId", technicianId)
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    trySend(Result.Error(error, error.localizedMessage ?: "Unknown error"))
                    return@addSnapshotListener
                }
                val list = snapshot?.documents?.mapNotNull { it.toObject(ReportDoc::class.java) } ?: emptyList()
                trySend(Result.Success(list))
            }
        awaitClose { listener.remove() }
    }

    override suspend fun getReports(technicianId: String, fromTimestamp: Long): List<ReportDoc> {
        val fromDate = Timestamp(java.util.Date(fromTimestamp))
        val snapshot = firestore.collection("reports")
            .whereEqualTo("technicianId", technicianId)
            .whereGreaterThanOrEqualTo("createdAt", fromDate)
            .get()
            .await()
        return snapshot.documents.mapNotNull { it.toObject(ReportDoc::class.java) }
    }

    override suspend fun resetReportStatus(reportId: String): Result<Unit> {
        return safeFirestoreCall {
            firestore.collection("reports")
                .document(reportId)
                .update(
                    "status", "pending",
                    "errorMessage", null
                )
                .await()
            Unit
        }
    }
}
