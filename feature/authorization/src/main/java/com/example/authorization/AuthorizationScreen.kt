package com.example.authorization

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.authorization.ui.AuthorizationContent
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun AuthorizationScreen(
    modifier: Modifier = Modifier,
    viewModel: AuthorizationViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }

    val uiState = viewModel.uiState.collectAsState()
    val token by viewModel.token.collectAsState()

    LaunchedEffect(viewModel.actions) {
        viewModel.actions.collectLatest { action ->
            when (action) {
                is AuthorizationViewModel.Action.RouteToMain -> {
                    Log.d("AuthorizationScreen", "RouteToMain")
                }

                // TODO: Заменить Snackbar на AlertDialog в соответствии с дизайн-макетом
                //  для детальных ошибок
                is AuthorizationViewModel.Action.ShowError -> {
                    val errorMessage = action.messageUiText.asString(context)

                    snackbarHostState.currentSnackbarData?.dismiss()
                    snackbarHostState.showSnackbar(
                        message = errorMessage,
                        withDismissAction = true
                    )
                }
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier
            )
        }
    ) { paddingValues ->
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
                        errorReason = currentState.reasonUiText.asString(),
                        onChangeToken = { viewModel.token.value = it },
                        onSignButtonPressed = viewModel::onSignButtonPressed
                    )
                }
            }
        }
    }
}