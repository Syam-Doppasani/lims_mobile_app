package com.labapp.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.labapp.core.util.Result
import com.labapp.data.model.UserDoc
import com.labapp.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _currentUser = MutableStateFlow<UserDoc?>(null)
    val currentUser: StateFlow<UserDoc?> = _currentUser.asStateFlow()

    private val _authState = MutableStateFlow<Result<UserDoc>?>(null)
    val authState: StateFlow<Result<UserDoc>?> = _authState.asStateFlow()

    private val _isApproved = MutableStateFlow(false)
    val isApproved: StateFlow<Boolean> = _isApproved.asStateFlow()

    private val _isLoadingApproval = MutableStateFlow(false)
    val isLoadingApproval: StateFlow<Boolean> = _isLoadingApproval.asStateFlow()

    init {
        viewModelScope.launch {
            authRepository.getCurrentUserFlow().collect { user ->
                _currentUser.value = user
                if (user != null) {
                    checkApprovalFromClaims()
                }
            }
        }
    }

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = Result.Loading
            val result = authRepository.signIn(email, password)
            _authState.value = result
        }
    }

    fun register(name: String, email: String, password: String, labName: String) {
        viewModelScope.launch {
            _authState.value = Result.Loading
            val result = authRepository.register(name, email, password, labName)
            _authState.value = result
        }
    }

    fun signOut() {
        viewModelScope.launch {
            authRepository.signOut()
            _authState.value = null
            _isApproved.value = false
        }
    }

    fun checkApprovalStatus() {
        viewModelScope.launch {
            _isLoadingApproval.value = true
            try {
                val result = firebaseAuth.currentUser?.getIdToken(true)?.await()
                val claims = result?.claims
                val approved = claims?.get("approved") as? Boolean ?: false
                _isApproved.value = approved
            } catch (e: Exception) {
                Timber.e(e, "Token refresh failed")
            } finally {
                _isLoadingApproval.value = false
            }
        }
    }

    private suspend fun checkApprovalFromClaims() {
        try {
            val result = firebaseAuth.currentUser?.getIdToken(false)?.await()
            val claims = result?.claims
            val approved = claims?.get("approved") as? Boolean ?: false
            _isApproved.value = approved
        } catch (e: Exception) {
            Timber.e(e, "Check claims failed")
        }
    }
}
