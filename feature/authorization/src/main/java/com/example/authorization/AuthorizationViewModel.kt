package com.example.authorization

import android.content.Context
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

    private val _token = MutableStateFlow("")
    val token = _token.asStateFlow()

    private val _uiState = MutableStateFlow<State>(State.Idle)
    val uiState = _uiState.asStateFlow()

    private val _actions = Channel<Action>()
    val actions = _actions.receiveAsFlow()

    fun onTokenChanged(newToken: String) {
        _token.value = newToken
    }

    fun onSignButtonPressed() {
        _uiState.value = State.Loading

        val currentToken = _token.value

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
                _actions.send(Action.Authorized)
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
        val isLoading: Boolean get() = false
        fun getError(context: Context): String = ""

        object Idle : State
        object Loading : State {
            override val isLoading = true
        }

        data class InvalidInput(val reasonUiText: UiText) : State {
            override fun getError(context: Context) = reasonUiText.asString(context)
        }
    }

    sealed interface Action {

        object Authorized : Action
        data class ShowError(val messageUiText: UiText) : Action
    }
}