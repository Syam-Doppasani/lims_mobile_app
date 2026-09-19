package com.labapp.presentation.technician

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.labapp.data.model.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateReportScreen(
    viewModel: CreateReportViewModel,
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState.saveSuccess) {
        if (uiState.saveSuccess) {
            onBackClick()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("New Report — Step ${uiState.currentStep + 1}/5") },
                navigationIcon = {
                    IconButton(onClick = {
                        if (uiState.currentStep > 0) {
                            viewModel.prevStep()
                        } else {
                            onBackClick()
                        }
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background)
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.background,
                            MaterialTheme.colorScheme.surface
                        )
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Main Step Content
                Box(modifier = Modifier.weight(1f)) {
                    when (uiState.currentStep) {
                        0 -> StepTestType(uiState = uiState, onSelect = viewModel::onTestTypeSelected)
                        1 -> StepReferralDoctor(uiState = uiState, onSelect = viewModel::onDoctorSelected)
                        2 -> StepPatientDetails(
                            uiState = uiState,
                            onNameChange = viewModel::updatePatientName,
                            onAgeChange = viewModel::updatePatientAge,
                            onSexChange = viewModel::updatePatientSex,
                            onMobileChange = viewModel::updatePatientMobile
                        )
                        3 -> StepEnterValues(
                            uiState = uiState,
                            onValueChange = viewModel::onValueChanged
                        )
                        4 -> StepReview(uiState = uiState)
                    }
                }

                // Bottom Action Buttons
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    if (uiState.currentStep > 0) {
                        OutlinedButton(
                            onClick = viewModel::prevStep,
                            modifier = Modifier.weight(1f).padding(end = 8.dp)
                        ) {
                            Text("Back")
                        }
                    }

                    if (uiState.currentStep < 4) {
                        Button(
                            onClick = viewModel::nextStep,
                            modifier = Modifier.weight(1f).padding(start = if (uiState.currentStep > 0) 8.dp else 0.dp)
                        ) {
                            Text("Continue")
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(Icons.Default.ArrowForward, contentDescription = null)
                        }
                    } else {
                        Button(
                            onClick = viewModel::submitReport,
                            modifier = Modifier.weight(1f).padding(start = 8.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                        ) {
                            Icon(Icons.Default.Check, contentDescription = null)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Submit & Send")
                        }
                    }
                }
            }

            if (uiState.isSaving) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background.copy(alpha = 0.5f)),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                }
            }

            uiState.error?.let { err ->
                AlertDialog(
                    onDismissRequest = viewModel::clearError,
                    title = { Text("Validation Alert") },
                    text = { Text(err) },
                    confirmButton = {
                        TextButton(onClick = viewModel::clearError) {
                            Text("Dismiss")
                        }
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StepTestType(
    uiState: CreateReportUiState,
    onSelect: (TemplateDoc) -> Unit
) {
    Column {
        Text("Select Test Type", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(uiState.templates) { template ->
                val isSelected = uiState.selectedTemplate?.templateId == template.templateId
                Card(
                    onClick = { onSelect(template) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surface
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(template.name, fontWeight = FontWeight.Bold)
                            Text(template.testType, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                        if (isSelected) {
                            Icon(Icons.Default.Check, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StepReferralDoctor(
    uiState: CreateReportUiState,
    onSelect: (ReferralDoctorDoc) -> Unit
) {
    Column {
        Text("Select Referral Doctor", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(uiState.referralDoctors) { doctor ->
                val isSelected = uiState.selectedDoctor?.doctorId == doctor.doctorId
                Card(
                    onClick = { onSelect(doctor) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surface
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = if (doctor.isSelf) "Self (send to patient)" else doctor.name,
                                fontWeight = FontWeight.Bold
                            )
                            if (!doctor.isSelf) {
                                Text(doctor.specialisation, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                Text(doctor.mobile, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                        }
                        if (isSelected) {
                            Icon(Icons.Default.Check, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StepPatientDetails(
    uiState: CreateReportUiState,
    onNameChange: (String) -> Unit,
    onAgeChange: (String) -> Unit,
    onSexChange: (String) -> Unit,
    onMobileChange: (String) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text("Patient Details", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

        OutlinedTextField(
            value = uiState.patientName,
            onValueChange = onNameChange,
            label = { Text("Patient Name") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            OutlinedTextField(
                value = uiState.patientAge,
                onValueChange = onAgeChange,
                label = { Text("Age (Years)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
                modifier = Modifier.weight(1f),
                singleLine = true
            )

            Column(modifier = Modifier.weight(1.5f)) {
                Text("Sex", style = MaterialTheme.typography.labelSmall, modifier = Modifier.padding(start = 4.dp, bottom = 4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    listOf("Male", "Female", "Other").forEach { sexOption ->
                        val isSelected = uiState.patientSex == sexOption
                        Button(
                            onClick = { onSexChange(sexOption) },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                                contentColor = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
                            ),
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(8.dp),
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Text(sexOption, fontSize = 12.sp)
                        }
                    }
                }
            }
        }

        OutlinedTextField(
            value = uiState.patientMobile,
            onValueChange = onMobileChange,
            label = { Text("Mobile Number (E.164 format)") },
            placeholder = { Text("+919876543210") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone, imeAction = ImeAction.Done),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Text("Enter mobile with country code (e.g. +919876543210)", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Composable
fun StepEnterValues(
    uiState: CreateReportUiState,
    onValueChange: (String, String) -> Unit
) {
    Column {
        Text("Enter Test Results", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            val template = uiState.selectedTemplate!!
            items(template.parameters) { parameter ->
                val value = uiState.parameterValues[parameter.id] ?: ""
                val flag = uiState.parameterFlags[parameter.id] ?: ValueFlag.NORMAL
                ParameterValueField(
                    parameter = parameter,
                    value = value,
                    flag = flag,
                    patientSex = uiState.patientSex,
                    onValueChange = { onValueChange(parameter.id, it) }
                )
            }
        }
    }
}

@Composable
fun ParameterValueField(
    parameter: Parameter,
    value: String,
    flag: ValueFlag,
    patientSex: String,
    onValueChange: (String) -> Unit
) {
    val (min, max) = if (patientSex.lowercase() == "female") {
        parameter.normalMinFemale to parameter.normalMaxFemale
    } else {
        parameter.normalMinMale to parameter.normalMaxMale
    }

    val flagIcon = when (flag) {
        ValueFlag.HIGH -> "↑"
        ValueFlag.LOW -> "↓"
        else -> ""
    }
    val isAbnormal = flag == ValueFlag.HIGH || flag == ValueFlag.LOW

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = parameter.name,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = if (isAbnormal) FontWeight.Bold else FontWeight.Normal
            )
            if (parameter.inputType == InputType.NUMBER) {
                Text(
                    text = "Ref: $min – $max ${parameter.unit}",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                if (isAbnormal) {
                    Text(
                        text = flagIcon,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                }
            },
            isError = isAbnormal,
            keyboardOptions = KeyboardOptions(
                keyboardType = if (parameter.inputType == InputType.NUMBER) KeyboardType.Number else KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            suffix = { Text(parameter.unit) },
            singleLine = true
        )
    }
}

@Composable
fun StepReview(
    uiState: CreateReportUiState
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text("Review Report details", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Patient: ${uiState.patientName}", fontWeight = FontWeight.Bold)
                Text("Age/Sex: ${uiState.patientAge} Yrs / ${uiState.patientSex}")
                Text("Mobile: ${uiState.patientMobile}")
                Divider(modifier = Modifier.padding(vertical = 4.dp))
                Text("Test Type: ${uiState.selectedTemplate?.name} (${uiState.selectedTemplate?.testType})")
                Text("Referral Doctor: " + (if (uiState.selectedDoctor?.isSelf == true) "Self" else uiState.selectedDoctor?.name ?: ""))
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Entered Results:", fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 8.dp))
                uiState.selectedTemplate?.parameters?.forEach { param ->
                    val valStr = uiState.parameterValues[param.id] ?: ""
                    val flag = uiState.parameterFlags[param.id] ?: ValueFlag.NORMAL
                    val isAbnormal = flag == ValueFlag.HIGH || flag == ValueFlag.LOW
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(param.name)
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = valStr + " " + param.unit,
                                fontWeight = if (isAbnormal) FontWeight.Bold else FontWeight.Normal,
                                color = if (isAbnormal) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface
                            )
                            if (isAbnormal) {
                                Text(
                                    text = if (flag == ValueFlag.HIGH) " ↑" else " ↓",
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
