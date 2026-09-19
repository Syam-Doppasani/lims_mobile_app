package com.labapp.data.repository

import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.labapp.core.util.Result
import com.labapp.core.util.safeFirestoreCall
import com.labapp.data.model.Parameter
import com.labapp.data.model.TemplateDoc
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TemplateRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) : TemplateRepository {

    override fun getTemplatesFlow(): Flow<Result<List<TemplateDoc>>> = callbackFlow {
        val listener = firestore.collection("templates")
            .orderBy("createdAt")
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    trySend(Result.Error(error, error.localizedMessage ?: "Unknown error"))
                    return@addSnapshotListener
                }
                val list = snapshot?.documents?.mapNotNull { it.toObject(TemplateDoc::class.java) } ?: emptyList()
                trySend(Result.Success(list))
            }
        awaitClose { listener.remove() }
    }

    override fun getAssignedTemplatesFlow(technicianId: String): Flow<Result<List<TemplateDoc>>> = callbackFlow {
        val userListener = firestore.collection("users").document(technicianId)
            .addSnapshotListener { userSnapshot, userError ->
                if (userError != null) {
                    trySend(Result.Error(userError, userError.localizedMessage ?: "Unknown error"))
                    return@addSnapshotListener
                }
                val assignedIds = userSnapshot?.get("assignedTemplateIds") as? List<String> ?: emptyList()
                if (assignedIds.isEmpty()) {
                    trySend(Result.Success(emptyList()))
                    return@addSnapshotListener
                }
                
                firestore.collection("templates")
                    .whereEqualTo("status", "active")
                    .whereIn("templateId", assignedIds)
                    .get()
                    .addOnSuccessListener { querySnapshot ->
                        val list = querySnapshot.documents.mapNotNull { it.toObject(TemplateDoc::class.java) }
                        trySend(Result.Success(list))
                    }
                    .addOnFailureListener { e ->
                        trySend(Result.Error(e, e.localizedMessage ?: "Unknown error"))
                    }
            }
        awaitClose { userListener.remove() }
    }

    override suspend fun saveTemplate(
        name: String,
        testType: String,
        parameters: List<Parameter>,
        status: String
    ): Result<Unit> {
        return safeFirestoreCall {
            val uid = auth.currentUser?.uid ?: throw Exception("Not authenticated")
            val id = UUID.randomUUID().toString()
            val newTemplate = TemplateDoc(
                templateId = id,
                name = name,
                testType = testType,
                parameters = parameters,
                status = status,
                createdBy = uid,
                createdAt = Timestamp.now(),
                updatedAt = Timestamp.now()
            )
            firestore.collection("templates").document(id).set(newTemplate).await()
            Unit
        }
    }

    override suspend fun assignTemplatesToUser(
        technicianId: String,
        templateIds: List<String>
    ): Result<Unit> {
        return safeFirestoreCall {
            val uid = auth.currentUser?.uid ?: throw Exception("Not authenticated")
            val batch = firestore.batch()
            
            val userRef = firestore.collection("users").document(technicianId)
            batch.update(userRef, "assignedTemplateIds", templateIds)
            
            templateIds.forEach { templateId ->
                val assignmentId = "${technicianId}_${templateId}"
                val assignmentRef = firestore.collection("templateAssignments").document(assignmentId)
                val data = hashMapOf(
                    "assignmentId" to assignmentId,
                    "technicianId" to technicianId,
                    "templateId" to templateId,
                    "assignedBy" to uid,
                    "assignedAt" to Timestamp.now()
                )
                batch.set(assignmentRef, data)
            }
            batch.commit().await()
            Unit
        }
    }
}
