package com.labapp.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.functions.FirebaseFunctions
import com.labapp.core.util.Result
import com.labapp.core.util.safeFirestoreCall
import com.labapp.data.model.UserDoc
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val functions: FirebaseFunctions
) : UserRepository {

    override fun getPendingUsers(): Flow<Result<List<UserDoc>>> = callbackFlow {
        val listener = firestore.collection("users")
            .whereEqualTo("role", "technician")
            .whereEqualTo("approvedAt", null)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    trySend(Result.Error(error, error.localizedMessage ?: "Unknown error"))
                    return@addSnapshotListener
                }
                val list = snapshot?.documents?.mapNotNull { it.toObject(UserDoc::class.java) } ?: emptyList()
                trySend(Result.Success(list))
            }
        awaitClose { listener.remove() }
    }

    override fun getActiveUsers(): Flow<Result<List<UserDoc>>> = callbackFlow {
        val listener = firestore.collection("users")
            .whereEqualTo("role", "technician")
            .whereNotEqualTo("approvedAt", null)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    trySend(Result.Error(error, error.localizedMessage ?: "Unknown error"))
                    return@addSnapshotListener
                }
                val list = snapshot?.documents?.mapNotNull { it.toObject(UserDoc::class.java) } ?: emptyList()
                trySend(Result.Success(list))
            }
        awaitClose { listener.remove() }
    }

    override suspend fun setUserRole(
        targetUserId: String,
        role: String,
        approved: Boolean
    ): Result<Unit> {
        return safeFirestoreCall {
            val data = hashMapOf(
                "targetUserId" to targetUserId,
                "role" to role,
                "approved" to approved
            )
            functions.getHttpsCallable("setUserRole")
                .call(data)
                .await()
            Unit
        }
    }
}
