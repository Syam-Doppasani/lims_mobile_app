package com.labapp.presentation.admin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.labapp.core.util.Result
import com.labapp.data.model.InputType
import com.labapp.data.model.Parameter
import com.labapp.data.repository.TemplateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

data class ParameterInput(
    val id: String = "",
    val name: String = "",
    val unit: String = "",
    val normalMinMale: String = "",
    val normalMaxMale: String = "",
    val normalMinFemale: String = "",
    val normalMaxFemale: String = "",
    val displayOrder: Int = 0,
    val inputType: InputType = InputType.NUMBER
)

data class TemplateEditorUiState(
    val templateName: String = "",
    val testType: String = "",
    val parameters: List<ParameterInput> = emptyList(),
    val isSaving: Boolean = false,
    val saveSuccess: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class TemplateEditorViewModel @Inject constructor(
    private val templateRepository: TemplateRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(TemplateEditorUiState())
    val uiState: StateFlow<TemplateEditorUiState> = _uiState.asStateFlow()

    fun updateName(name: String) {
        _uiState.update { it.copy(templateName = name) }
    }

    fun updateTestType(type: String) {
        _uiState.update { it.copy(testType = type) }
    }

    fun addParameter() {
        val newParam = ParameterInput(
            id = UUID.randomUUID().toString(),
            name = "",
            unit = "",
            normalMinMale = "",
            normalMaxMale = "",
            normalMinFemale = "",
            normalMaxFemale = "",
            displayOrder = _uiState.value.parameters.size,
            inputType = InputType.NUMBER
        )
        _uiState.update { it.copy(parameters = it.parameters + newParam) }
    }

    fun updateParameter(index: Int, updated: ParameterInput) {
        val params = _uiState.value.parameters.toMutableList()
        if (index in params.indices) {
            params[index] = updated
            _uiState.update { it.copy(parameters = params) }
        }
    }

    fun removeParameter(index: Int) {
        val params = _uiState.value.parameters.toMutableList()
        if (index in params.indices) {
            params.removeAt(index)
            val renumbered = params.mapIndexed { i, p -> p.copy(displayOrder = i) }
            _uiState.update { it.copy(parameters = renumbered) }
        }
    }

    fun moveParameterUp(index: Int) {
        if (index <= 0 || index >= _uiState.value.parameters.size) return
        val params = _uiState.value.parameters.toMutableList()
        val temp = params[index]
        params[index] = params[index - 1]
        params[index - 1] = temp
        _uiState.update { it.copy(parameters = params) }
    }

    fun saveTemplate(publish: Boolean) {
        viewModelScope.launch {
            val state = _uiState.value
            val validationError = validateTemplate(state)
            if (validationError != null) {
                _uiState.update { it.copy(error = validationError) }
                return@launch
            }

            _uiState.update { it.copy(isSaving = true, error = null) }

            val mappedParameters = state.parameters.map { p ->
                Parameter(
                    id = p.id,
                    name = p.name,
                    unit = p.unit,
                    normalMinMale = p.normalMinMale.toDoubleOrNull() ?: 0.0,
                    normalMaxMale = p.normalMaxMale.toDoubleOrNull() ?: 0.0,
                    normalMinFemale = p.normalMinFemale.toDoubleOrNull() ?: 0.0,
                    normalMaxFemale = p.normalMaxFemale.toDoubleOrNull() ?: 0.0,
                    displayOrder = p.displayOrder,
                    inputType = p.inputType
                )
            }

            val result = templateRepository.saveTemplate(
                name = state.templateName,
                testType = state.testType,
                parameters = mappedParameters,
                status = if (publish) "active" else "draft"
            )

            when (result) {
                is Result.Success -> _uiState.update { it.copy(isSaving = false, saveSuccess = true) }
                is Result.Error -> _uiState.update { it.copy(isSaving = false, error = result.message) }
                else -> {}
            }
        }
    }

    private fun validateTemplate(state: TemplateEditorUiState): String? {
        if (state.templateName.isBlank()) return "Template name is required."
        if (state.testType.isBlank()) return "Test type is required."
        if (state.parameters.isEmpty()) return "Add at least one parameter."
        state.parameters.forEach { param ->
            if (param.name.isBlank()) return "All parameters must have a name."
            if (param.unit.isBlank()) return "All parameters must have a unit."
            val minM = param.normalMinMale.toDoubleOrNull()
            val maxM = param.normalMaxMale.toDoubleOrNull()
            val minF = param.normalMinFemale.toDoubleOrNull()
            val maxF = param.normalMaxFemale.toDoubleOrNull()
            if (minM == null || maxM == null || minF == null || maxF == null) {
                return "Parameter '${param.name}': all reference ranges must be valid numbers."
            }
            if (minM >= maxM) return "Parameter '${param.name}': male min must be less than male max."
            if (minF >= maxF) return "Parameter '${param.name}': female min must be less than female max."
        }
        return null
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
}
