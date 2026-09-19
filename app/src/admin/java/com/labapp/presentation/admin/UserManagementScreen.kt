package com.labapp.presentation.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.labapp.data.model.UserDoc
import com.labapp.data.model.TemplateDoc

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserManagementScreen(
    viewModel: UserManagementViewModel,
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    var selectedUserForApproval by remember { mutableStateOf<UserDoc?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("User Management") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
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
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {
                item {
                    Text(
                        text = "Pending Registrations",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }

                if (uiState.pendingUsers.isEmpty()) {
                    item {
                        Text(
                            text = "No pending approvals.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(vertical = 16.dp)
                        )
                    }
                } else {
                    items(uiState.pendingUsers) { user ->
                        PendingUserItem(
                            user = user,
                            onApproveClick = { selectedUserForApproval = user }
                        )
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Approved Technicians",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }

                if (uiState.activeUsers.isEmpty()) {
                    item {
                        Text(
                            text = "No active technicians.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(vertical = 16.dp)
                        )
                    }
                } else {
                    items(uiState.activeUsers) { user ->
                        ActiveUserItem(user = user, templates = uiState.templates)
                    }
                }
            }

            if (uiState.isProcessing) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background.copy(alpha = 0.5f)),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                }
            }

            selectedUserForApproval?.let { user ->
                var selectedTemplateIds by remember { mutableStateOf(emptyList<String>()) }
                
                AlertDialog(
                    onDismissRequest = { selectedUserForApproval = null },
                    title = { Text("Approve & Assign Templates") },
                    text = {
                        Column {
                            Text("Select templates to assign to ${user.name}:")
                            Spacer(modifier = Modifier.height(8.dp))
                            LazyColumn(modifier = Modifier.heightIn(max = 200.dp)) {
                                items(uiState.templates.filter { it.status == "active" }) { template ->
                                    val isSelected = selectedTemplateIds.contains(template.templateId)
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Checkbox(
                                            checked = isSelected,
                                            onCheckedChange = { checked ->
                                                selectedTemplateIds = if (checked) {
                                                    selectedTemplateIds + template.templateId
                                                } else {
                                                    selectedTemplateIds - template.templateId
                                                }
                                            }
                                        )
                                        Text(template.name)
                                    }
                                }
                            }
                        }
                    },
                    confirmButton = {
                        Button(onClick = {
                            viewModel.approveUser(user.userId, selectedTemplateIds)
                            selectedUserForApproval = null
                        }) {
                            Text("Approve")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { selectedUserForApproval = null }) {
                            Text("Cancel")
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun PendingUserItem(
    user: UserDoc,
    onApproveClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
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
                Text(user.name, fontWeight = FontWeight.Bold)
                Text(user.email, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text("Lab: ${user.labName}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Button(onClick = onApproveClick) {
                Icon(Icons.Default.Check, contentDescription = "Approve")
                Spacer(modifier = Modifier.width(4.dp))
                Text("Approve")
            }
        }
    }
}

@Composable
fun ActiveUserItem(
    user: UserDoc,
    templates: List<TemplateDoc>
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(user.name, fontWeight = FontWeight.Bold)
            Text(user.email, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Assigned templates: " + (if (user.assignedTemplateIds.isEmpty()) "None" else {
                    user.assignedTemplateIds.mapNotNull { id ->
                        templates.find { it.templateId == id }?.name
                    }.joinToString(", ")
                }),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}
