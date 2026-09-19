package com.labapp.data.repository

import com.labapp.core.util.Result
import com.labapp.data.model.UserDoc
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getPendingUsers(): Flow<Result<List<UserDoc>>>
    fun getActiveUsers(): Flow<Result<List<UserDoc>>>
    suspend fun setUserRole(targetUserId: String, role: String, approved: Boolean): Result<Unit>
}
