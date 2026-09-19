package com.labapp.presentation.technician

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.labapp.core.util.Result
import com.labapp.data.model.ReferralDoctorDoc
import com.labapp.data.repository.ReferralDoctorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

data class DoctorUiState(
    val isLoading: Boolean = false,
    val doctors: List<ReferralDoctorDoc> = emptyList(),
    val isSaving: Boolean = false,
    val saveSuccess: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class DoctorViewModel @Inject constructor(
    private val doctorRepository: ReferralDoctorRepository,
    private val auth: FirebaseAuth
) : ViewModel() {

    private val _uiState = MutableStateFlow(DoctorUiState())
    val uiState: StateFlow<DoctorUiState> = _uiState.asStateFlow()

    init {
        loadDoctors()
    }

    fun loadDoctors() {
        val userId = auth.currentUser?.uid ?: return
        _uiState.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            doctorRepository.getDoctorsFlow(userId).collect { result ->
                when (result) {
                    is Result.Success -> _uiState.update { it.copy(isLoading = false, doctors = result.data) }
                    is Result.Error -> _uiState.update { it.copy(isLoading = false, error = result.message) }
                    else -> {}
                }
            }
        }
    }

    fun addDoctor(name: String, mobile: String, specialisation: String) {
        viewModelScope.launch {
            if (name.isBlank()) {
                _uiState.update { it.copy(error = "Doctor name is required.") }
                return@launch
            }
            if (!mobile.matches(Regex("^\\+[1-9]\\d{9,14}$"))) {
                _uiState.update { it.copy(error = "Enter a valid mobile number in E.164 format (e.g. +919876543210).") }
                return@launch
            }

            _uiState.update { it.copy(isSaving = true, error = null, saveSuccess = false) }

            val userId = auth.currentUser?.uid ?: return@launch
            val id = UUID.randomUUID().toString()
            val newDoc = ReferralDoctorDoc(
                doctorId = id,
                name = name,
                mobile = mobile,
                specialisation = specialisation,
                isSelf = false,
                technicianId = userId,
                createdAt = Timestamp.now()
            )

            when (val result = doctorRepository.createDoctor(newDoc)) {
                is Result.Success -> _uiState.update { it.copy(isSaving = false, saveSuccess = true) }
                is Result.Error -> _uiState.update { it.copy(isSaving = false, error = result.message) }
                else -> {}
            }
        }
    }

    fun deleteDoctor(doctor: ReferralDoctorDoc) {
        if (doctor.isSelf) {
            _uiState.update { it.copy(error = "Cannot delete the 'Self' entry.") }
            return
        }
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            val result = doctorRepository.deleteDoctor(doctor.doctorId)
            if (result is Result.Error) {
                _uiState.update { it.copy(isLoading = false, error = result.message) }
            } else {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }

    fun resetSaveSuccess() {
        _uiState.update { it.copy(saveSuccess = false) }
    }
}
