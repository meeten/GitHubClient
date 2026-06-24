package com.example.githubclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.authorization.AuthorizationScreen
import com.example.designsystem.GitHubClientTheme
import com.example.githubclient.navigation.AppNavGraph
import com.example.githubclient.navigation.rememberNavigationState
import com.example.home.HomeScreen
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            val viewModel: MainViewModel = koinViewModel()
            val navigationState = rememberNavigationState()

            GitHubClientTheme {
                AppNavGraph(
                    navController = navigationState.navController,
                    startDestination = viewModel.getStartDestination(),
                    authorizationScreen = {
                        AuthorizationScreen(onAuthorized = navigationState::navigateToHome)
                    },
                    homeScreen = {
                        HomeScreen(
                            onLogoutButtonPressed = {
                                viewModel.onLogoutButtonPressed()
                                navigationState.navigateToAuthorization()
                            }
                        )
                    }
                )
            }
        }
    }
}