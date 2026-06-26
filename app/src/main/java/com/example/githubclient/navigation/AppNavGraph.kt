package com.example.githubclient.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
internal fun AppNavGraph(
    navController: NavHostController,
    startDestination: String,
    authorizationScreen: @Composable () -> Unit,
    homeScreen: @Composable () -> Unit,
    repoInfoScreen: @Composable (Int, String) -> Unit
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

        composable(
            route = Screen.RepoInfo.route,
            arguments = listOf(
                navArgument(name = Screen.KEY_REPO_ID) {
                    type = NavType.IntType
                }, navArgument(name = Screen.KEY_PEPO_NAME) {
                    type = NavType.StringType
                })
        ) {
            val repoId = it.arguments?.getInt(Screen.KEY_REPO_ID) ?: -UNDEFINED_ID
            val repoName = it.arguments?.getString(Screen.KEY_PEPO_NAME) ?: EMPTY_STRING
            repoInfoScreen(repoId, repoName)
        }
    }
}

private const val UNDEFINED_ID = -1
private const val EMPTY_STRING = ""