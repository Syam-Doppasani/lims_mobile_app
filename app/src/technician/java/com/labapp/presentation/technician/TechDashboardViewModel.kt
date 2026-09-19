package com.labapp.presentation.technician

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.labapp.core.util.Result
import com.labapp.domain.model.DashboardData
import com.labapp.domain.usecase.EnsureSelfDoctorUseCase
import com.labapp.domain.usecase.GetDashboardDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class TechDashboardUiState(
    val isLoading: Boolean = false,
    val dashboardData: DashboardData? = null,
    val error: String? = null
)

@HiltViewModel
class TechDashboardViewModel @Inject constructor(
    private val getDashboardDataUseCase: GetDashboardDataUseCase,
    private val ensureSelfDoctorUseCase: EnsureSelfDoctorUseCase,
    private val auth: FirebaseAuth
) : ViewModel() {

    private val _uiState = MutableStateFlow(TechDashboardUiState())
    val uiState: StateFlow<TechDashboardUiState> = _uiState.asStateFlow()

    init {
        ensureSelfDoctorCreated()
        loadDashboardData()
    }

    private fun ensureSelfDoctorCreated() {
        viewModelScope.launch {
            ensureSelfDoctorUseCase()
        }
    }

    fun loadDashboardData() {
        viewModelScope.launch {
            val userId = auth.currentUser?.uid ?: return@launch
            _uiState.update { it.copy(isLoading = true, error = null) }
            
            when (val result = getDashboardDataUseCase(userId)) {
                is Result.Success -> _uiState.update { it.copy(isLoading = false, dashboardData = result.data) }
                is Result.Error -> _uiState.update { it.copy(isLoading = false, error = result.message) }
                else -> {}
            }
        }
    }
}
