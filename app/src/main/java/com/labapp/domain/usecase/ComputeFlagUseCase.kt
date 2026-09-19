package com.labapp.domain.usecase

import com.labapp.data.model.InputType
import com.labapp.data.model.Parameter
import com.labapp.data.model.ValueFlag
import javax.inject.Inject

class ComputeFlagUseCase @Inject constructor() {
    operator fun invoke(rawValue: String, parameter: Parameter, sex: String): ValueFlag {
        if (parameter.inputType == InputType.TEXT) return ValueFlag.NA
        val numericValue = rawValue.toDoubleOrNull() ?: return ValueFlag.NA
        val (min, max) = when (sex.lowercase()) {
            "female" -> parameter.normalMinFemale to parameter.normalMaxFemale
            else -> parameter.normalMinMale to parameter.normalMaxMale
        }
        return when {
            numericValue < min -> ValueFlag.LOW
            numericValue > max -> ValueFlag.HIGH
            else -> ValueFlag.NORMAL
        }
    }
}
