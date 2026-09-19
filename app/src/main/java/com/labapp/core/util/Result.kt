package com.labapp.core.util

import com.google.firebase.firestore.FirebaseFirestoreException
import timber.log.Timber

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception, val message: String) : Result<Nothing>()
    object Loading : Result<Nothing>()
}

suspend fun <T> safeFirestoreCall(block: suspend () -> T): Result<T> {
    return try {
        Result.Success(block())
    } catch (e: FirebaseFirestoreException) {
        Timber.e(e, "Firestore error: ${e.code}")
        Result.Error(e, mapFirestoreError(e.code))
    } catch (e: Exception) {
        Timber.e(e, "Unexpected error")
        Result.Error(e, "An unexpected error occurred. Please try again.")
    }
}

fun mapFirestoreError(code: FirebaseFirestoreException.Code): String = when (code) {
    FirebaseFirestoreException.Code.PERMISSION_DENIED -> "You don't have permission to perform this action."
    FirebaseFirestoreException.Code.NOT_FOUND -> "The requested data was not found."
    FirebaseFirestoreException.Code.UNAVAILABLE -> "Service unavailable. Please check your internet connection."
    FirebaseFirestoreException.Code.DEADLINE_EXCEEDED -> "Request timed out. Please try again."
    else -> "A network error occurred. Please try again."
}
