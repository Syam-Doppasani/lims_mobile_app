package com.labapp.presentation.technician

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.labapp.core.util.Result
import com.labapp.data.model.ReportDoc
import com.labapp.data.repository.ReportRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ReportListUiState(
    val isLoading: Boolean = false,
    val reports: List<ReportDoc> = emptyList(),
    val isRetrying: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class ReportListViewModel @Inject constructor(
    private val reportRepository: ReportRepository,
    private val auth: FirebaseAuth
) : ViewModel() {

    private val _uiState = MutableStateFlow(ReportListUiState())
    val uiState: StateFlow<ReportListUiState> = _uiState.asStateFlow()

    init {
        loadReports()
    }

    fun loadReports() {
        val userId = auth.currentUser?.uid ?: return
        _uiState.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            reportRepository.getReportsFlow(userId).collect { result ->
                when (result) {
                    is Result.Success -> _uiState.update { it.copy(isLoading = false, reports = result.data) }
                    is Result.Error -> _uiState.update { it.copy(isLoading = false, error = result.message) }
                    else -> {}
                }
            }
        }
    }

    fun retryFailedReport(reportId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isRetrying = true, error = null) }
            val result = reportRepository.resetReportStatus(reportId)
            when (result) {
                is Result.Success -> _uiState.update { it.copy(isRetrying = false) }
                is Result.Error -> _uiState.update { it.copy(isRetrying = false, error = result.message) }
                else -> {}
            }
        }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
}
