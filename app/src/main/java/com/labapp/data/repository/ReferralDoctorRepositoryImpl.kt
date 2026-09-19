package com.labapp.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.labapp.core.util.Result
import com.labapp.core.util.safeFirestoreCall
import com.labapp.data.model.ReferralDoctorDoc
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReferralDoctorRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : ReferralDoctorRepository {

    override fun getDoctorsFlow(technicianId: String): Flow<Result<List<ReferralDoctorDoc>>> = callbackFlow {
        val listener = firestore.collection("referralDoctors")
            .whereEqualTo("technicianId", technicianId)
            .orderBy("name", Query.Direction.ASCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    trySend(Result.Error(error, error.localizedMessage ?: "Unknown error"))
                    return@addSnapshotListener
                }
                val list = snapshot?.documents?.mapNotNull { it.toObject(ReferralDoctorDoc::class.java) } ?: emptyList()
                trySend(Result.Success(list))
            }
        awaitClose { listener.remove() }
    }

    override suspend fun getSelfDoctor(technicianId: String): ReferralDoctorDoc? {
        val querySnapshot = firestore.collection("referralDoctors")
            .whereEqualTo("technicianId", technicianId)
            .whereEqualTo("isSelf", true)
            .limit(1)
            .get()
            .await()
        return querySnapshot.documents.firstOrNull()?.toObject(ReferralDoctorDoc::class.java)
    }

    override suspend fun createDoctor(doctor: ReferralDoctorDoc): Result<Unit> {
        return safeFirestoreCall {
            firestore.collection("referralDoctors")
                .document(doctor.doctorId)
                .set(doctor)
                .await()
            Unit
        }
    }

    override suspend fun deleteDoctor(doctorId: String): Result<Unit> {
        return safeFirestoreCall {
            firestore.collection("referralDoctors")
                .document(doctorId)
                .delete()
                .await()
            Unit
        }
    }
}
