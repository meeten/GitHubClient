package com.example.githubclient.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.rememberNavController

internal class NavigationState(val navController: NavHostController) {

    fun navigateToHome() {
        navController.navigate(Screen.Home.route) {
            setting()
        }
    }

    fun navigateToAuthorization() {
        navController.navigate(Screen.Authorization.route) {
            setting()
        }
    }

    private fun NavOptionsBuilder.setting() {
        popUpTo(navController.graph.startDestinationId) {
            inclusive = true
        }

        launchSingleTop = true
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