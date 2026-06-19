package com.example.githubclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.authorization.AuthorizationScreen
import com.example.designsystem.GitHubClientTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            val viewModel: MainViewModel = koinViewModel()
            val action = viewModel.action

            GitHubClientTheme {
                when (action) {
                    MainViewModel.Action.RouteAuthorization -> {
                        AuthorizationScreen(modifier = Modifier.padding(16.dp))
                    }

                    MainViewModel.Action.RouteToHome -> {
                        Box {
                            Text(text = "Welcome to HomeScreen")
                        }
                    }
                }
            }
        }
    }
}