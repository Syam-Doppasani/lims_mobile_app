package com.labapp.data.model

import com.google.firebase.Timestamp

data class UserDoc(
    val userId: String = "",
    val name: String = "",
    val email: String = "",
    val role: String = "technician", // "admin" | "technician"
    val labName: String = "",
    val labLogoUrl: String? = null,
    val assignedTemplateIds: List<String> = emptyList(),
    val approvedAt: Timestamp? = null,
    val approvedBy: String? = null,
    val createdAt: Timestamp? = null,
    val isActive: Boolean = true
)
