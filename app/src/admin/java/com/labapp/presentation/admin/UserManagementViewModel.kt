package com.labapp.presentation.admin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.labapp.core.util.Result
import com.labapp.data.model.UserDoc
import com.labapp.data.model.TemplateDoc
import com.labapp.data.repository.TemplateRepository
import com.labapp.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class UserManagementUiState(
    val pendingUsers: List<UserDoc> = emptyList(),
    val activeUsers: List<UserDoc> = emptyList(),
    val templates: List<TemplateDoc> = emptyList(),
    val isProcessing: Boolean = false,
    val approvalSuccess: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class UserManagementViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val templateRepository: TemplateRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UserManagementUiState())
    val uiState: StateFlow<UserManagementUiState> = _uiState.asStateFlow()

    init {
        loadPendingUsers()
        loadActiveUsers()
        loadTemplates()
    }

    private fun loadPendingUsers() {
        viewModelScope.launch {
            userRepository.getPendingUsers().collect { result ->
                when (result) {
                    is Result.Success -> _uiState.update { it.copy(pendingUsers = result.data) }
                    is Result.Error -> _uiState.update { it.copy(error = result.message) }
                    else -> {}
                }
            }
        }
    }

    private fun loadActiveUsers() {
        viewModelScope.launch {
            userRepository.getActiveUsers().collect { result ->
                when (result) {
                    is Result.Success -> _uiState.update { it.copy(activeUsers = result.data) }
                    is Result.Error -> _uiState.update { it.copy(error = result.message) }
                    else -> {}
                }
            }
        }
    }

    private fun loadTemplates() {
        viewModelScope.launch {
            templateRepository.getTemplatesFlow().collect { result ->
                when (result) {
                    is Result.Success -> _uiState.update { it.copy(templates = result.data) }
                    is Result.Error -> _uiState.update { it.copy(error = result.message) }
                    else -> {}
                }
            }
        }
    }

    fun approveUser(userId: String, assignedTemplateIds: List<String>) {
        viewModelScope.launch {
            _uiState.update { it.copy(isProcessing = true, error = null, approvalSuccess = false) }
            val claimResult = userRepository.setUserRole(
                targetUserId = userId,
                role = "technician",
                approved = true
            )
            if (claimResult is Result.Error) {
                _uiState.update { it.copy(isProcessing = false, error = claimResult.message) }
                return@launch
            }
            val assignResult = templateRepository.assignTemplatesToUser(
                technicianId = userId,
                templateIds = assignedTemplateIds
            )
            when (assignResult) {
                is Result.Success -> _uiState.update { it.copy(isProcessing = false, approvalSuccess = true) }
                is Result.Error -> _uiState.update { it.copy(isProcessing = false, error = assignResult.message) }
                else -> {}
            }
        }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
}
