package com.labapp.data.model

import com.google.firebase.Timestamp

data class TemplateAssignmentDoc(
    val assignmentId: String = "",
    val technicianId: String = "",
    val templateId: String = "",
    val assignedBy: String = "",
    val assignedAt: Timestamp? = null
)
