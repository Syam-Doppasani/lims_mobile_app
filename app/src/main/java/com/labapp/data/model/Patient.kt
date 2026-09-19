package com.labapp.data.model

import com.google.firebase.Timestamp

data class PatientDoc(
    val patientId: String = "",
    val name: String = "",
    val age: Int = 0,
    val sex: String = "Male", // "Male" | "Female" | "Other"
    val mobile: String = "",
    val technicianId: String = "",
    val createdAt: Timestamp? = null
)
