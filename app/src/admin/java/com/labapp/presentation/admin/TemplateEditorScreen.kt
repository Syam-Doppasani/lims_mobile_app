package com.labapp.presentation.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowUp
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TemplateEditorScreen(
    viewModel: TemplateEditorViewModel,
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
                title = { Text("Create Template") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    TextButton(onClick = { viewModel.saveTemplate(publish = false) }) {
                        Text("Draft")
                    }
                    Button(onClick = { viewModel.saveTemplate(publish = true) }) {
                        Text("Publish")
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
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    OutlinedTextField(
                        value = uiState.templateName,
                        onValueChange = viewModel::updateName,
                        label = { Text("Template Name (e.g. Complete Blood Count)") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                item {
                    OutlinedTextField(
                        value = uiState.testType,
                        onValueChange = viewModel::updateTestType,
                        label = { Text("Test Code / Type (e.g. CBC)") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                item {
                    Divider(modifier = Modifier.padding(vertical = 8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Test Parameters",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Button(onClick = viewModel::addParameter) {
                            Text("Add Parameter")
                        }
                    }
                }

                itemsIndexed(uiState.parameters) { index, param ->
                    ParameterEditorItem(
                        param = param,
                        onUpdate = { updated -> viewModel.updateParameter(index, updated) },
                        onRemove = { viewModel.removeParameter(index) },
                        onMoveUp = { viewModel.moveParameterUp(index) },
                        isFirst = index == 0
                    )
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
                    title = { Text("Validation Error") },
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

@Composable
fun ParameterEditorItem(
    param: ParameterInput,
    onUpdate: (ParameterInput) -> Unit,
    onRemove: () -> Unit,
    onMoveUp: () -> Unit,
    isFirst: Boolean
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Parameter #${param.displayOrder + 1}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                Row {
                    if (!isFirst) {
                        IconButton(onClick = onMoveUp) {
                            Icon(Icons.Default.KeyboardArrowUp, contentDescription = "Move Up")
                        }
                    }
                    IconButton(onClick = onRemove) {
                        Icon(Icons.Default.Delete, contentDescription = "Remove", tint = MaterialTheme.colorScheme.error)
                    }
                }
            }

            OutlinedTextField(
                value = param.name,
                onValueChange = { onUpdate(param.copy(name = it)) },
                label = { Text("Parameter Name (e.g. Haemoglobin)") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = param.unit,
                onValueChange = { onUpdate(param.copy(unit = it)) },
                label = { Text("Unit (e.g. g/dL)") },
                modifier = Modifier.fillMaxWidth()
            )

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = param.normalMinMale,
                    onValueChange = { onUpdate(param.copy(normalMinMale = it)) },
                    label = { Text("Min Male") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
                    modifier = Modifier.weight(1f)
                )
                OutlinedTextField(
                    value = param.normalMaxMale,
                    onValueChange = { onUpdate(param.copy(normalMaxMale = it)) },
                    label = { Text("Max Male") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
                    modifier = Modifier.weight(1f)
                )
            }

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = param.normalMinFemale,
                    onValueChange = { onUpdate(param.copy(normalMinFemale = it)) },
                    label = { Text("Min Female") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
                    modifier = Modifier.weight(1f)
                )
                OutlinedTextField(
                    value = param.normalMaxFemale,
                    onValueChange = { onUpdate(param.copy(normalMaxFemale = it)) },
                    label = { Text("Max Female") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}
