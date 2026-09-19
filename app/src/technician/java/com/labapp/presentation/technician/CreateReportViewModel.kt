package com.labapp.presentation.technician

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.labapp.core.util.Result
import com.labapp.data.model.*
import com.labapp.data.repository.*
import com.labapp.domain.usecase.ComputeFlagUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

data class CreateReportUiState(
    val isLoading: Boolean = false,
    val templates: List<TemplateDoc> = emptyList(),
    val selectedTemplate: TemplateDoc? = null,
    val referralDoctors: List<ReferralDoctorDoc> = emptyList(),
    val selectedDoctor: ReferralDoctorDoc? = null,
    val patientName: String = "",
    val patientAge: String = "",
    val patientSex: String = "Male",
    val patientMobile: String = "",
    val parameterValues: Map<String, String> = emptyMap(),
    val parameterFlags: Map<String, ValueFlag> = emptyMap(),
    val currentStep: Int = 0,
    val isSaving: Boolean = false,
    val saveSuccess: Boolean = false,
    val savedReportId: String? = null,
    val error: String? = null
)

@HiltViewModel
class CreateReportViewModel @Inject constructor(
    private val templateRepository: TemplateRepository,
    private val doctorRepository: ReferralDoctorRepository,
    private val reportRepository: ReportRepository,
    private val authRepository: AuthRepository,
    private val computeFlagUseCase: ComputeFlagUseCase,
    private val auth: FirebaseAuth
) : ViewModel() {

    private val _uiState = MutableStateFlow(CreateReportUiState())
    val uiState: StateFlow<CreateReportUiState> = _uiState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        val userId = auth.currentUser?.uid ?: return
        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            templateRepository.getAssignedTemplatesFlow(userId).collect { result ->
                if (result is Result.Success) {
                    _uiState.update { it.copy(templates = result.data) }
                }
            }
        }

        viewModelScope.launch {
            doctorRepository.getDoctorsFlow(userId).collect { result ->
                if (result is Result.Success) {
                    _uiState.update { it.copy(referralDoctors = result.data) }
                }
            }
        }
    }

    fun nextStep() {
        val state = _uiState.value
        val next = state.currentStep + 1
        val error = validateStep(state.currentStep)
        if (error != null) {
            _uiState.update { it.copy(error = error) }
        } else {
            _uiState.update { it.copy(currentStep = next, error = null) }
        }
    }

    fun prevStep() {
        val state = _uiState.value
        if (state.currentStep > 0) {
            _uiState.update { it.copy(currentStep = state.currentStep - 1, error = null) }
        }
    }

    fun onTestTypeSelected(template: TemplateDoc) {
        _uiState.update { state ->
            state.copy(
                selectedTemplate = template,
                parameterValues = emptyMap(),
                parameterFlags = emptyMap(),
                currentStep = 1
            )
        }
    }

    fun onDoctorSelected(doctor: ReferralDoctorDoc) {
        _uiState.update { it.copy(selectedDoctor = doctor) }
    }

    fun updatePatientName(name: String) {
        _uiState.update { it.copy(patientName = name) }
    }

    fun updatePatientAge(age: String) {
        _uiState.update { it.copy(patientAge = age) }
    }

    fun updatePatientSex(sex: String) {
        _uiState.update { it.copy(patientSex = sex) }
    }

    fun updatePatientMobile(mobile: String) {
        _uiState.update { it.copy(patientMobile = mobile) }
    }

    fun onValueChanged(parameterId: String, rawValue: String) {
        val state = _uiState.value
        val template = state.selectedTemplate ?: return
        val parameter = template.parameters.find { it.id == parameterId } ?: return
        val patientSex = state.patientSex

        val flag = computeFlagUseCase(rawValue, parameter, patientSex)

        _uiState.update {
            it.copy(
                parameterValues = it.parameterValues + (parameterId to rawValue),
                parameterFlags = it.parameterFlags + (parameterId to flag)
            )
        }
    }

    private fun validateStep(step: Int): String? {
        val state = _uiState.value
        return when (step) {
            0 -> if (state.selectedTemplate == null) "Please select a test type." else null
            1 -> if (state.selectedDoctor == null) "Please select a referral doctor." else null
            2 -> {
                if (state.patientName.isBlank()) return "Patient name is required."
                val age = state.patientAge.toIntOrNull()
                if (age == null || age <= 0) return "Enter a valid age."
                if (!state.patientMobile.matches(Regex("^\\+[1-9]\\d{9,14}$"))) {
                    return "Enter a valid mobile number in international format (e.g. +919876543210)."
                }
                null
            }
            3 -> {
                val template = state.selectedTemplate ?: return "No template selected."
                template.parameters.forEach { param ->
                    val value = state.parameterValues[param.id] ?: ""
                    if (value.isBlank()) return "Please enter a value for '${param.name}'."
                    if (param.inputType == InputType.NUMBER && value.toDoubleOrNull() == null) {
                        return "'${param.name}' must be a valid number."
                    }
                }
                null
            }
            else -> null
        }
    }

    fun submitReport() {
        viewModelScope.launch {
            val state = _uiState.value
            val error = validateStep(3) ?: validateStep(2) ?: validateStep(1) ?: validateStep(0)
            if (error != null) {
                _uiState.update { it.copy(error = error) }
                return@launch
            }

            _uiState.update { it.copy(isSaving = true, error = null) }

            val userId = auth.currentUser?.uid ?: return@launch
            val userDoc = authRepository.getCurrentUser() ?: return@launch

            val reportId = UUID.randomUUID().toString()
            val patientId = UUID.randomUUID().toString()

            val valuesList = state.selectedTemplate!!.parameters.map { p ->
                val valStr = state.parameterValues[p.id] ?: ""
                ReportValue(
                    parameterId = p.id,
                    parameterName = p.name,
                    unit = p.unit,
                    value = valStr,
                    numericValue = valStr.toDoubleOrNull(),
                    normalMin = if (state.patientSex.lowercase() == "female") p.normalMinFemale else p.normalMinMale,
                    normalMax = if (state.patientSex.lowercase() == "female") p.normalMaxFemale else p.normalMaxMale,
                    flag = state.parameterFlags[p.id] ?: ValueFlag.NORMAL
                )
            }

            val reportDoc = ReportDoc(
                reportId = reportId,
                patientId = patientId,
                patientSnapshot = PatientSnapshot(
                    name = state.patientName,
                    age = state.patientAge.toInt(),
                    sex = state.patientSex,
                    mobile = state.patientMobile
                ),
                templateId = state.selectedTemplate.templateId,
                testType = state.selectedTemplate.testType,
                referralDoctorId = state.selectedDoctor!!.doctorId,
                referralDoctorSnapshot = ReferralDoctorSnapshot(
                    name = state.selectedDoctor.name,
                    mobile = state.selectedDoctor.mobile,
                    isSelf = state.selectedDoctor.isSelf
                ),
                technicianId = userId,
                technicianSnapshot = TechnicianSnapshot(
                    name = userDoc.name,
                    labName = userDoc.labName,
                    labLogoUrl = userDoc.labLogoUrl
                ),
                values = valuesList,
                status = "pending",
                createdAt = Timestamp.now()
            )

            val result = reportRepository.createReport(reportDoc)

            when (result) {
                is Result.Success -> _uiState.update { it.copy(isSaving = false, saveSuccess = true, savedReportId = reportId) }
                is Result.Error -> _uiState.update { it.copy(isSaving = false, error = result.message) }
                else -> {}
            }
        }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
}
