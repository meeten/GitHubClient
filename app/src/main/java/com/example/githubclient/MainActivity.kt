package com.example.githubclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.authorization.AuthScreen
import com.example.designsystem.GitHubClientTheme
import com.example.githubclient.navigation.AppNavGraph
import com.example.githubclient.navigation.rememberNavigationState
import com.example.home.HomeScreen
import com.example.repo_info.RepositoryInfoScreen
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
                        AuthScreen(onAuthorized = navigationState::navigateToHome)
                    },
                    homeScreen = {
                        HomeScreen(
                            onLogoutButtonPressed = {
                                viewModel.onLogoutButtonPressed()
                                navigationState.navigateToAuthorization()
                            },
                            onRepoClick = navigationState::navigateToRepoInfo
                        )
                    },
                    repoInfoScreen = { id, name ->
                        RepositoryInfoScreen(
                            repoId = id,
                            repoName = name,
                            modifier = Modifier.padding(16.dp),
                            onBackButtonPressed = {
                                viewModel.onButtonPressed {
                                    navigationState.navController.popBackStack()
                                }
                            },
                            onLogoutButtonPressed = viewModel::onLogoutButtonPressed
                        )
                    }
                )
            }
        }
    }
}