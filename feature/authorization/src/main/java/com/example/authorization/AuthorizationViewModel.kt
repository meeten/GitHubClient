package com.example.authorization

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.extension.onFailure
import com.example.domain.model.OperationResult
import com.example.domain.usecase.ValidateTokenUseCase
import com.example.ui.UiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AuthorizationViewModel(
    private val validateTokenUseCase: ValidateTokenUseCase
) : ViewModel() {

    val token = MutableStateFlow("")

    private val _uiState = MutableStateFlow<State>(State.Idle)
    val uiState = _uiState.asStateFlow()

    private val _actions = Channel<Action>()
    val actions = _actions.receiveAsFlow()

    fun onSignButtonPressed() {
        _uiState.value = State.Loading

        val currentToken = token.value

        if (currentToken.isBlank()) {
            _uiState.value = State.InvalidInput(
                reasonUiText = UiText.StringResource(resId = R.string.invalid_token)
            )
            return
        }

        viewModelScope.launch {
            validateToken(currentToken)
        }
    }

    private suspend fun validateToken(token: String) {
        when (val result = validateTokenUseCase(token)) {
            is OperationResult.Success -> {
                _uiState.value = State.Idle
                _actions.send(Action.RouteToMain)
            }

            is OperationResult.Failure -> {
                result.onFailure(
                    onInvalidToken = {
                        _uiState.value =
                            State.InvalidInput(
                                reasonUiText = UiText.StringResource(resId = R.string.invalid_token)
                            )
                    },
                    onNetworkError = {
                        _uiState.value = State.Idle
                        _actions.send(
                            Action.ShowError(
                                messageUiText = UiText.StringResource(resId = R.string.network_error)
                            )
                        )
                    },
                    onUnknownError = {
                        _uiState.value = State.Idle
                        _actions.send(
                            Action.ShowError(
                                messageUiText = UiText.DynamicString(value = it)
                            )
                        )
                    },
                )
            }
        }
    }

    sealed interface State {

        object Idle : State
        object Loading : State
        data class InvalidInput(val reasonUiText: UiText) : State
    }

    sealed interface Action {

        object RouteToMain : Action
        data class ShowError(val messageUiText: UiText) : Action
    }
}