package com.labapp.data.repository

import com.labapp.core.util.Result
import com.labapp.data.model.UserDoc
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun getCurrentUserFlow(): Flow<UserDoc?>
    suspend fun getCurrentUser(): UserDoc?
    suspend fun signIn(email: String, password: String): Result<UserDoc>
    suspend fun register(name: String, email: String, password: String, labName: String): Result<UserDoc>
    suspend fun signOut()
}
