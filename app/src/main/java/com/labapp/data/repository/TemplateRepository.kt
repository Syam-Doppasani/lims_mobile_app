package com.labapp.data.repository

import com.labapp.core.util.Result
import com.labapp.data.model.Parameter
import com.labapp.data.model.TemplateDoc
import kotlinx.coroutines.flow.Flow

interface TemplateRepository {
    fun getTemplatesFlow(): Flow<Result<List<TemplateDoc>>>
    fun getAssignedTemplatesFlow(technicianId: String): Flow<Result<List<TemplateDoc>>>
    suspend fun saveTemplate(name: String, testType: String, parameters: List<Parameter>, status: String): Result<Unit>
    suspend fun assignTemplatesToUser(technicianId: String, templateIds: List<String>): Result<Unit>
}
