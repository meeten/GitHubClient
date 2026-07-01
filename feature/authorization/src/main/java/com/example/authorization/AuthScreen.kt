package com.example.authorization

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
fun AuthScreen(
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel = koinViewModel(),
    onAuthorized: () -> Unit
) {
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }

    val uiState by viewModel.uiState.collectAsState()
    val token by viewModel.token.collectAsState()

    LaunchedEffect(viewModel.actions) {
        viewModel.actions.collectLatest { action ->
            when (action) {
                AuthViewModel.Action.Authorized -> {
                    onAuthorized()
                }

                // TODO: Заменить Snackbar на AlertDialog в соответствии с дизайн-макетом
                //  для детальных ошибок
                is AuthViewModel.Action.ShowError -> {
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
        AuthorizationContent(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .imePadding(),
            token = token,
            isLoading = uiState.isLoading,
            errorReason = uiState.getError(context),
            onChangeToken = viewModel::onTokenChanged,
            onSignButtonPressed = viewModel::onSignButtonPressed
        )
    }
}