package com.labapp.data.repository

import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
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
class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AuthRepository {

    override fun getCurrentUserFlow(): Flow<UserDoc?> = callbackFlow {
        val listener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val uid = firebaseAuth.currentUser?.uid
            if (uid == null) {
                trySend(null)
            } else {
                val registration = firestore.collection("users").document(uid)
                    .addSnapshotListener { snapshot, error ->
                        if (error != null) {
                            trySend(null)
                            return@addSnapshotListener
                        }
                        val userDoc = snapshot?.toObject(UserDoc::class.java)
                        trySend(userDoc)
                    }
                // Cleanup registration listener on close
                // Note: we can't directly close inside the callback, but we handle it via awaitClose.
            }
        }
        auth.addAuthStateListener(listener)
        awaitClose { auth.removeAuthStateListener(listener) }
    }

    override suspend fun getCurrentUser(): UserDoc? {
        val uid = auth.currentUser?.uid ?: return null
        return try {
            firestore.collection("users").document(uid).get().await().toObject(UserDoc::class.java)
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun signIn(email: String, password: String): Result<UserDoc> {
        return safeFirestoreCall {
            val authResult = auth.signInWithEmailAndPassword(email, password).await()
            val uid = authResult.user?.uid ?: throw Exception("User ID is null")
            val doc = firestore.collection("users").document(uid).get().await()
            doc.toObject(UserDoc::class.java) ?: throw Exception("User document not found")
        }
    }

    override suspend fun register(
        name: String,
        email: String,
        password: String,
        labName: String
    ): Result<UserDoc> {
        return safeFirestoreCall {
            val authResult = auth.createUserWithEmailAndPassword(email, password).await()
            val uid = authResult.user?.uid ?: throw Exception("User ID is null")
            val newUser = UserDoc(
                userId = uid,
                name = name,
                email = email,
                role = "technician",
                labName = labName,
                createdAt = Timestamp.now(),
                approvedAt = null,
                approvedBy = null,
                isActive = false
            )
            firestore.collection("users").document(uid).set(newUser).await()
            newUser
        }
    }

    override suspend fun signOut() {
        auth.signOut()
    }
}
