package com.labapp.domain.usecase

import com.google.firebase.Timestamp
import com.labapp.core.util.Result
import com.labapp.core.util.safeFirestoreCall
import com.labapp.data.repository.ReportRepository
import com.labapp.domain.model.DashboardData
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

class GetDashboardDataUseCase @Inject constructor(
    private val reportRepository: ReportRepository
) {
    suspend operator fun invoke(technicianId: String): Result<DashboardData> {
        return safeFirestoreCall {
            val now = System.currentTimeMillis()
            val thirtyDaysAgo = now - (30L * 24 * 60 * 60 * 1000)

            val reports = reportRepository.getReports(
                technicianId = technicianId,
                fromTimestamp = thirtyDaysAgo
            )

            val today = reports.filter { isToday(it.createdAt) }
            val thisWeek = reports.filter { isThisWeek(it.createdAt) }

            // Reports per day (last 30 days) for line chart
            val reportsPerDay = reports
                .groupBy { formatDate(it.createdAt) }
                .mapValues { it.value.size }

            // Test type breakdown for bar chart
            val testTypeBreakdown = reports
                .groupBy { it.testType }
                .mapValues { it.value.size }
                .entries
                .sortedByDescending { it.value }
                .take(10)

            // Top referral doctors
            val topDoctors = reports
                .groupBy { it.referralDoctorSnapshot.name }
                .mapValues { it.value.size }
                .entries
                .sortedByDescending { it.value }
                .take(5)

            // Unique patients
            val uniquePatients = reports.map { it.patientId }.toSet().size

            DashboardData(
                todayCount = today.size,
                thisWeekCount = thisWeek.size,
                thisMonthCount = reports.size,
                uniquePatients = uniquePatients,
                reportsPerDay = reportsPerDay,
                testTypeBreakdown = testTypeBreakdown,
                topReferralDoctors = topDoctors
            )
        }
    }

    private fun isToday(timestamp: Timestamp?): Boolean {
        if (timestamp == null) return false
        val date = timestamp.toDate()
        val today = Calendar.getInstance()
        val reportDate = Calendar.getInstance().apply { time = date }
        return today.get(Calendar.YEAR) == reportDate.get(Calendar.YEAR) &&
                today.get(Calendar.DAY_OF_YEAR) == reportDate.get(Calendar.DAY_OF_YEAR)
    }

    private fun isThisWeek(timestamp: Timestamp?): Boolean {
        if (timestamp == null) return false
        val date = timestamp.toDate()
        val now = System.currentTimeMillis()
        val sevenDaysAgo = now - (7L * 24 * 60 * 60 * 1000)
        return date.time >= sevenDaysAgo
    }

    private fun formatDate(timestamp: Timestamp?): String {
        if (timestamp == null) return ""
        val sdf = SimpleDateFormat("dd MMM", Locale.getDefault())
        return sdf.format(timestamp.toDate())
    }
}
