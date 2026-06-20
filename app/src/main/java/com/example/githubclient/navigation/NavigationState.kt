package com.example.githubclient.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

internal class NavigationState(val navController: NavHostController) {

    fun navigateToHome() {
        navController.navigate(Screen.Home.route) {
            popUpTo(navController.graph.startDestinationId) {
                inclusive = true
            }

            launchSingleTop = true
        }
    }
}

@Composable
internal fun rememberNavigationState(
    navController: NavHostController = rememberNavController()
): NavigationState {
    return remember {
        NavigationState(navController)
    }
}