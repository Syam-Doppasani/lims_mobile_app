package com.labapp.domain.model

data class DashboardData(
    val todayCount: Int = 0,
    val thisWeekCount: Int = 0,
    val thisMonthCount: Int = 0,
    val uniquePatients: Int = 0,
    val reportsPerDay: Map<String, Int> = emptyMap(),
    val testTypeBreakdown: List<Map.Entry<String, Int>> = emptyList(),
    val topReferralDoctors: List<Map.Entry<String, Int>> = emptyList()
)
