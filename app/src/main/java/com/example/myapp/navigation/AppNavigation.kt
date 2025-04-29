package com.example.myapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapp.ui.HomeScreen
import com.example.myapp.ui.LoginScreen
import androidx.lifecycle.viewmodel.compose.viewModel // Keep viewModel import if LoginScreen uses it internally
import com.example.myapp.viewmodel.LoginViewModel // Keep ViewModel import

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            // Get the ViewModel instance here if needed, or let LoginScreen handle it
            val loginViewModel: LoginViewModel = viewModel()
            LoginScreen(
                viewModel = loginViewModel, // Pass the ViewModel explicitly
                onLoginSuccess = {
                    // Navigate to home, clear login from back stack
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }
        composable("home") {
            HomeScreen(
                onLogoutClick = {
                    // Navigate back to login, clear the entire back stack up to login
                    navController.navigate("login") {
                        popUpTo(navController.graph.startDestinationId) { // More robust way to get start destination
                            inclusive = true
                        }
                        // Ensure only one copy of login screen exists
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}
