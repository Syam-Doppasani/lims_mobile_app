package com.labapp.data.repository

import com.labapp.core.util.Result
import com.labapp.data.model.ReferralDoctorDoc
import kotlinx.coroutines.flow.Flow

interface ReferralDoctorRepository {
    fun getDoctorsFlow(technicianId: String): Flow<Result<List<ReferralDoctorDoc>>>
    suspend fun getSelfDoctor(technicianId: String): ReferralDoctorDoc?
    suspend fun createDoctor(doctor: ReferralDoctorDoc): Result<Unit>
    suspend fun deleteDoctor(doctorId: String): Result<Unit>
}
