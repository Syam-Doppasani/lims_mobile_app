package com.labapp.data.model

import com.google.firebase.Timestamp

enum class InputType { NUMBER, TEXT }

data class Parameter(
    val id: String = "",
    val name: String = "",
    val unit: String = "",
    val normalMinMale: Double = 0.0,
    val normalMaxMale: Double = 0.0,
    val normalMinFemale: Double = 0.0,
    val normalMaxFemale: Double = 0.0,
    val displayOrder: Int = 0,
    val inputType: InputType = InputType.NUMBER
)

data class TemplateDoc(
    val templateId: String = "",
    val name: String = "",
    val testType: String = "",
    val parameters: List<Parameter> = emptyList(),
    val status: String = "draft", // "draft" | "active"
    val createdBy: String = "",
    val createdAt: Timestamp? = null,
    val updatedAt: Timestamp? = null
)
