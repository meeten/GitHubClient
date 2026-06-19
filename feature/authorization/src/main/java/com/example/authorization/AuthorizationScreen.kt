package com.example.authorization

import android.util.Log
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

    val uiState by viewModel.uiState.collectAsState()
    val token by viewModel.token.collectAsState()

    val stateValue = uiState
    val isLoading = stateValue is AuthorizationViewModel.State.Loading

    val errorReason = if (stateValue is AuthorizationViewModel.State.InvalidInput) {
        stateValue.reasonUiText.asString(context)
    } else {
        ""
    }

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
        AuthorizationContent(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .imePadding(),
            token = token,
            isLoading = isLoading,
            errorReason = errorReason,
            onChangeToken = viewModel::onTokenChanged,
            onSignButtonPressed = viewModel::onSignButtonPressed
        )
    }
}