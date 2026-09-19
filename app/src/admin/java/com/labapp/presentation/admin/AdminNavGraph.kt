package com.labapp.presentation.admin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.labapp.presentation.auth.AuthViewModel
import com.labapp.presentation.auth.LoginScreen

@Composable
fun AdminNavGraph() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = hiltViewModel()
    val currentUser by authViewModel.currentUser.collectAsState()

    val startDestination = if (currentUser == null) "login" else "dashboard"

    NavHost(navController = navController, startDestination = startDestination) {
        composable("login") {
            LoginScreen(
                viewModel = authViewModel,
                onRegisterClick = { /* Admins cannot register from the app, only technicians */ },
                onLoginSuccess = {
                    navController.navigate("dashboard") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        composable("dashboard") {
            AdminDashboardScreen(
                authViewModel = authViewModel,
                onNavigateToUserManagement = { navController.navigate("user_management") },
                onNavigateToTemplateList = { navController.navigate("templates") },
                onSignOut = {
                    navController.navigate("login") {
                        popUpTo("dashboard") { inclusive = true }
                    }
                }
            )
        }

        composable("user_management") {
            val userMgmtViewModel: UserManagementViewModel = hiltViewModel()
            UserManagementScreen(
                viewModel = userMgmtViewModel,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable("templates") {
            val userMgmtViewModel: UserManagementViewModel = hiltViewModel()
            TemplateListScreen(
                viewModel = userMgmtViewModel,
                onNavigateToEditor = { navController.navigate("template_editor") },
                onBackClick = { navController.popBackStack() }
            )
        }

        composable("template_editor") {
            val editorViewModel: TemplateEditorViewModel = hiltViewModel()
            TemplateEditorScreen(
                viewModel = editorViewModel,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
