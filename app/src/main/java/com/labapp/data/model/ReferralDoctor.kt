package com.labapp.data.model

import com.google.firebase.Timestamp

data class ReferralDoctorDoc(
    val doctorId: String = "",
    val name: String = "",
    val mobile: String = "",
    val specialisation: String = "",
    val isSelf: Boolean = false,
    val technicianId: String = "",
    val createdAt: Timestamp? = null
)
