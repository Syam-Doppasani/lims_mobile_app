package com.labapp.presentation.technician

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import com.labapp.presentation.auth.AuthViewModel
import com.labapp.presentation.auth.LoginScreen
import com.labapp.presentation.auth.PendingApprovalScreen
import com.labapp.presentation.auth.RegisterScreen

@Composable
fun TechnicianNavGraph() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = hiltViewModel()
    val currentUser by authViewModel.currentUser.collectAsState()
    val isApproved by authViewModel.isApproved.collectAsState()

    val startDestination = when {
        currentUser == null -> "login"
        !isApproved -> "pending_approval"
        else -> "main_scaffold"
    }

    NavHost(navController = navController, startDestination = startDestination) {
        composable("login") {
            LoginScreen(
                viewModel = authViewModel,
                onRegisterClick = { navController.navigate("register") },
                onLoginSuccess = {
                    val dest = if (authViewModel.isApproved.value) "main_scaffold" else "pending_approval"
                    navController.navigate(dest) {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        composable("register") {
            RegisterScreen(
                viewModel = authViewModel,
                onLoginClick = { navController.navigate("login") },
                onRegisterSuccess = {
                    navController.navigate("pending_approval") {
                        popUpTo("register") { inclusive = true }
                    }
                }
            )
        }

        composable("pending_approval") {
            PendingApprovalScreen(
                viewModel = authViewModel,
                onApproved = {
                    navController.navigate("main_scaffold") {
                        popUpTo("pending_approval") { inclusive = true }
                    }
                },
                onSignOut = {
                    authViewModel.signOut()
                    navController.navigate("login") {
                        popUpTo("pending_approval") { inclusive = true }
                    }
                }
            )
        }

        composable("main_scaffold") {
            MainScaffold(
                authViewModel = authViewModel,
                onLogout = {
                    navController.navigate("login") {
                        popUpTo("main_scaffold") { inclusive = true }
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(
    authViewModel: AuthViewModel,
    onLogout: () -> Unit
) {
    val nestedController = rememberNavController()
    val context = LocalContext.current

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by nestedController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                NavigationBarItem(
                    selected = currentRoute == "dashboard",
                    onClick = {
                        nestedController.navigate("dashboard") {
                            popUpTo(nestedController.graph.findStartDestination().id) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Dashboard") },
                    label = { Text("Dashboard") }
                )

                NavigationBarItem(
                    selected = currentRoute == "create_report",
                    onClick = {
                        nestedController.navigate("create_report") {
                            popUpTo(nestedController.graph.findStartDestination().id) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = { Icon(Icons.Default.AddCircle, contentDescription = "New Report") },
                    label = { Text("New Report") }
                )

                NavigationBarItem(
                    selected = currentRoute?.startsWith("reports") == true,
                    onClick = {
                        nestedController.navigate("reports") {
                            popUpTo(nestedController.graph.findStartDestination().id) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = { Icon(Icons.Default.List, contentDescription = "Reports") },
                    label = { Text("Reports") }
                )

                NavigationBarItem(
                    selected = currentRoute == "doctors",
                    onClick = {
                        nestedController.navigate("doctors") {
                            popUpTo(nestedController.graph.findStartDestination().id) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = { Icon(Icons.Default.Person, contentDescription = "Doctors") },
                    label = { Text("Doctors") }
                )
            }
        }
    ) { padding ->
        val reportListViewModel: ReportListViewModel = hiltViewModel()

        NavHost(
            navController = nestedController,
            startDestination = "dashboard",
            modifier = Modifier.padding(padding)
        ) {
            composable("dashboard") {
                val dashboardViewModel: TechDashboardViewModel = hiltViewModel()
                TechDashboardScreen(
                    viewModel = dashboardViewModel,
                    onNavigateToCreateReport = { nestedController.navigate("create_report") }
                )
            }

            composable("create_report") {
                val createReportViewModel: CreateReportViewModel = hiltViewModel()
                CreateReportScreen(
                    viewModel = createReportViewModel,
                    onBackClick = { nestedController.navigate("dashboard") }
                )
            }

            composable("reports") {
                ReportListScreen(
                    viewModel = reportListViewModel,
                    onNavigateToDetail = { id -> nestedController.navigate("reports/$id") }
                )
            }

            composable("reports/{reportId}") { backStackEntry ->
                val id = backStackEntry.arguments?.getString("reportId") ?: ""
                val reportActionsViewModel: ReportActionsViewModel = hiltViewModel()
                ReportDetailScreen(
                    reportId = id,
                    viewModel = reportListViewModel,
                    actionsViewModel = reportActionsViewModel,
                    onBackClick = { nestedController.popBackStack() },
                    onShareClick = { url ->
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        context.startActivity(intent)
                    }
                )
            }

            composable("doctors") {
                val doctorViewModel: DoctorViewModel = hiltViewModel()
                DoctorListScreen(viewModel = doctorViewModel)
            }
        }
    }
}
