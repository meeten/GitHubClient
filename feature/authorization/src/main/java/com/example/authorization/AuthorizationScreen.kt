package com.example.authorization

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.authorization.ui.AuthorizationContent
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun AuthorizationScreen(
    modifier: Modifier = Modifier,
    viewModel: AuthorizationViewModel = koinViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()
    val token by viewModel.token.collectAsState()

    LaunchedEffect(viewModel.actions) {
        viewModel.actions.collectLatest { action ->
            when (action) {
                is AuthorizationViewModel.Action.RouteToMain -> {
                    Log.d("AuthorizationScreen", "RouteToMain")
                }

                is AuthorizationViewModel.Action.ShowError -> {
                    Log.d("AuthorizationScreen", "Error: ${action.message}")
                }
            }

        }
    }

    Scaffold { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .imePadding()
        ) {
            when (val currentState = uiState.value) {
                AuthorizationViewModel.State.Idle -> {
                    AuthorizationContent(
                        token = token,
                        onChangeToken = { viewModel.token.value = it },
                        onSignButtonPressed = viewModel::onSignButtonPressed
                    )
                }

                AuthorizationViewModel.State.Loading -> {
                    AuthorizationContent(
                        token = token,
                        isLoading = true
                    )
                }

                is AuthorizationViewModel.State.InvalidInput -> {
                    AuthorizationContent(
                        token = token,
                        errorReason = currentState.reason,
                        onChangeToken = { viewModel.token.value = it },
                        onSignButtonPressed = viewModel::onSignButtonPressed
                    )
                }
            }
        }
    }
}