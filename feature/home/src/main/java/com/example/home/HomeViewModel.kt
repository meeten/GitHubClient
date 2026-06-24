package com.example.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.extension.onFailure
import com.example.domain.model.OperationResult
import com.example.domain.model.Repo
import com.example.domain.usecase.GetReposUseCase
import com.example.ui.R
import com.example.ui.UiText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getReposUseCase: GetReposUseCase
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<State>(State.Loading)
    val uiState = _uiState.asStateFlow()


    init {
        loadRepos()
    }

    fun onRetryButtonPressed() {
        loadRepos()
    }

    fun onRefreshButtonPressed() {
        loadRepos()
    }

    private fun loadRepos() {
        _uiState.value = State.Loading
        viewModelScope.launch {
            val result = getReposUseCase()
            handleRepositoriesResult(result)
        }
    }

    private fun handleRepositoriesResult(result: OperationResult<List<Repo>>) {
        when (result) {
            is OperationResult.Success -> {
                val repos = result.data
                _uiState.value =
                    if (repos.isEmpty()) State.Empty
                    else State.Loaded(repos = result.data)
            }

            is OperationResult.Failure -> {
                result.onFailure(
                    onNetworkError = {
                        _uiState.value =
                            State.Error(
                                errorUiText = UiText.StringResource(resId = R.string.network_error)
                            )
                    },
                    onUnknownError = {
                        Log.d("HomeViewModel", "Error: $it")
                        _uiState.value = State.Error(
                            errorUiText = UiText.StringResource(resId = R.string.check_your_something)
                        )
                    }
                )
            }
        }
    }

    sealed interface State {

        object Empty : State

        object Loading : State

        data class Loaded(val repos: List<Repo>) : State

        data class Error(val errorUiText: UiText) : State
    }
}