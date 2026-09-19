package com.labapp.domain.usecase

import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.labapp.core.util.Result
import com.labapp.core.util.safeFirestoreCall
import com.labapp.data.model.ReferralDoctorDoc
import com.labapp.data.repository.ReferralDoctorRepository
import java.util.UUID
import javax.inject.Inject

class EnsureSelfDoctorUseCase @Inject constructor(
    private val doctorRepository: ReferralDoctorRepository,
    private val auth: FirebaseAuth
) {
    suspend operator fun invoke(): Result<Unit> {
        val userId = auth.currentUser?.uid
            ?: return Result.Error(Exception("Not authenticated"), "Not authenticated")

        return safeFirestoreCall {
            val existingSelf = doctorRepository.getSelfDoctor(userId)
            if (existingSelf == null) {
                doctorRepository.createDoctor(
                    ReferralDoctorDoc(
                        doctorId = UUID.randomUUID().toString(),
                        name = "Self",
                        mobile = "", // will be replaced by patient mobile at report time
                        specialisation = "",
                        isSelf = true,
                        technicianId = userId,
                        createdAt = Timestamp.now()
                    )
                )
            }
        }
    }
}
