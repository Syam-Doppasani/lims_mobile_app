package com.labapp.data.repository

import com.labapp.core.util.Result
import com.labapp.data.model.ReportDoc
import kotlinx.coroutines.flow.Flow

interface ReportRepository {
    suspend fun createReport(report: ReportDoc): Result<String>
    fun getReportsFlow(technicianId: String): Flow<Result<List<ReportDoc>>>
    suspend fun getReports(technicianId: String, fromTimestamp: Long): List<ReportDoc>
    suspend fun resetReportStatus(reportId: String): Result<Unit>
}
