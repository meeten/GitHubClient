package com.example.githubclient.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
internal fun AppNavGraph(
    navController: NavHostController,
    startDestination: String,
    authorizationScreen: @Composable () -> Unit,
    homeScreen: @Composable () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screen.Authorization.route) {
            authorizationScreen()
        }

        composable(route = Screen.Home.route) {
            homeScreen()
        }
    }
}