package com.labapp.data.model

import com.google.firebase.Timestamp

enum class ValueFlag { LOW, HIGH, NORMAL, NA }

data class ReportValue(
    val parameterId: String = "",
    val parameterName: String = "",
    val unit: String = "",
    val value: String = "",
    val numericValue: Double? = null,
    val normalMin: Double? = null,
    val normalMax: Double? = null,
    val flag: ValueFlag = ValueFlag.NORMAL
)

data class ReportDoc(
    val reportId: String = "",
    val patientId: String = "",
    val patientSnapshot: PatientSnapshot = PatientSnapshot(),
    val templateId: String = "",
    val testType: String = "",
    val referralDoctorId: String = "",
    val referralDoctorSnapshot: ReferralDoctorSnapshot = ReferralDoctorSnapshot(),
    val technicianId: String = "",
    val technicianSnapshot: TechnicianSnapshot = TechnicianSnapshot(),
    val values: List<ReportValue> = emptyList(),
    val status: String = "pending", // "pending" | "processing" | "done" | "failed"
    val pdfUrl: String? = null,
    val pdfStoragePath: String? = null,
    val qrToken: String? = null,
    val qrTokenExpiresAt: Timestamp? = null,
    val whatsappSentAt: Timestamp? = null,
    val whatsappRecipientMobile: String? = null,
    val errorMessage: String? = null,
    val createdAt: Timestamp? = null,
    val completedAt: Timestamp? = null
)

data class PatientSnapshot(
    val name: String = "",
    val age: Int = 0,
    val sex: String = "Male",
    val mobile: String = ""
)

data class ReferralDoctorSnapshot(
    val name: String = "",
    val mobile: String = "",
    val isSelf: Boolean = false
)

data class TechnicianSnapshot(
    val name: String = "",
    val labName: String = "",
    val labLogoUrl: String? = null
)
