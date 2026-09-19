package com.labapp.presentation.technician

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.labapp.data.model.ReferralDoctorDoc

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoctorListScreen(
    viewModel: DoctorViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    var showAddDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Referral Doctors") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background)
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Add Doctor")
            }
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
            if (uiState.isLoading && uiState.doctors.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                }
            } else if (uiState.doctors.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No referral doctors registered.", color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(uiState.doctors) { doctor ->
                        DoctorItem(
                            doctor = doctor,
                            onDeleteClick = { viewModel.deleteDoctor(doctor) }
                        )
                    }
                }
            }

            if (showAddDialog) {
                var name by remember { mutableStateOf("") }
                var mobile by remember { mutableStateOf("") }
                var specialisation by remember { mutableStateOf("") }

                AlertDialog(
                    onDismissRequest = { showAddDialog = false },
                    title = { Text("Add Referral Doctor") },
                    text = {
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            OutlinedTextField(
                                value = name,
                                onValueChange = { name = it },
                                label = { Text("Full Name") },
                                modifier = Modifier.fillMaxWidth()
                            )
                            OutlinedTextField(
                                value = mobile,
                                onValueChange = { mobile = it },
                                label = { Text("Mobile (+919876543210)") },
                                placeholder = { Text("+919876543210") },
                                modifier = Modifier.fillMaxWidth()
                            )
                            OutlinedTextField(
                                value = specialisation,
                                onValueChange = { specialisation = it },
                                label = { Text("Specialisation") },
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    },
                    confirmButton = {
                        Button(onClick = {
                            viewModel.addDoctor(name, mobile, specialisation)
                            showAddDialog = false
                        }) {
                            Text("Save")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showAddDialog = false }) {
                            Text("Cancel")
                        }
                    }
                )
            }

            uiState.error?.let { err ->
                AlertDialog(
                    onDismissRequest = viewModel::clearError,
                    title = { Text("Alert") },
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
fun DoctorItem(
    doctor: ReferralDoctorDoc,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
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
            if (!doctor.isSelf) {
                IconButton(onClick = onDeleteClick) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete", tint = MaterialTheme.colorScheme.error)
                }
            }
        }
    }
}
