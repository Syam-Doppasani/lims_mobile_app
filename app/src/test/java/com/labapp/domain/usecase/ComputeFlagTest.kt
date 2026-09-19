package com.labapp.domain.usecase

import com.labapp.data.model.InputType
import com.labapp.data.model.Parameter
import com.labapp.data.model.ValueFlag
import org.junit.Assert.assertEquals
import org.junit.Test

class ComputeFlagTest {

    private val useCase = ComputeFlagUseCase()

    private fun buildParameter(
        minMale: Double = 0.0,
        maxMale: Double = 0.0,
        minFemale: Double = 0.0,
        maxFemale: Double = 0.0,
        inputType: InputType = InputType.NUMBER
    ): Parameter {
        return Parameter(
            id = "test-id",
            name = "Test Param",
            unit = "mg/dL",
            normalMinMale = minMale,
            normalMaxMale = maxMale,
            normalMinFemale = minFemale,
            normalMaxFemale = maxFemale,
            displayOrder = 0,
            inputType = inputType
        )
    }

    @Test
    fun `value below male min returns LOW`() {
        val param = buildParameter(minMale = 13.0, maxMale = 17.0)
        assertEquals(ValueFlag.LOW, useCase("12.0", param, "Male"))
    }

    @Test
    fun `value above female max returns HIGH`() {
        val param = buildParameter(minFemale = 12.0, maxFemale = 15.0)
        assertEquals(ValueFlag.HIGH, useCase("16.0", param, "Female"))
    }

    @Test
    fun `text parameter always returns NA`() {
        val param = buildParameter(inputType = InputType.TEXT)
        assertEquals(ValueFlag.NA, useCase("anything", param, "Male"))
    }

    @Test
    fun `non-numeric value returns NA`() {
        val param = buildParameter(minMale = 1.0, maxMale = 5.0)
        assertEquals(ValueFlag.NA, useCase("abc", param, "Male"))
    }

    @Test
    fun `value exactly at min is NORMAL`() {
        val param = buildParameter(minMale = 13.0, maxMale = 17.0)
        assertEquals(ValueFlag.NORMAL, useCase("13.0", param, "Male"))
    }

    @Test
    fun `value exactly at max is NORMAL`() {
        val param = buildParameter(minMale = 13.0, maxMale = 17.0)
        assertEquals(ValueFlag.NORMAL, useCase("17.0", param, "Male"))
    }
}
