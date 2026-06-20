package com.example.githubclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.authorization.AuthorizationScreen
import com.example.designsystem.GitHubClientTheme
import com.example.githubclient.navigation.AppNavGraph
import com.example.githubclient.navigation.rememberNavigationState
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
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Welcome home screen",
                                fontSize = 32.sp
                            )
                        }
                    }
                )
            }
        }
    }
}