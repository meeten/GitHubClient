package com.example.repo_info

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.OperationResult
import com.example.domain.model.RepoDetails
import com.example.domain.usecase.GetRepoByIdUseCase
import com.example.domain.usecase.GetRepositoryReadmeUseCase
import com.example.ui.R
import com.example.ui.UiText
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

@OptIn(ExperimentalCoroutinesApi::class)
class RepositoryInfoViewModel(
    private val getRepoByIdUseCase: GetRepoByIdUseCase,
    private val getRepositoryReadmeUseCase: GetRepositoryReadmeUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val uiState: StateFlow<State> = savedStateHandle.getStateFlow<Int?>(KEY_REPO_ID, null)
        .filterNotNull()
        .flatMapLatest { id ->
            flow {
                emit(State.Loading)

                val repoResult = getRepoByIdUseCase(id)

                if (repoResult is OperationResult.Failure) {
                    emit(State.Error(repoResult.toUiText()))
                    return@flow
                }

                val details = (repoResult as OperationResult.Success).data

                emit(State.Loaded(githubRepo = details, readmeState = ReadmeState.Loading))

                val readmeResult = getRepositoryReadmeUseCase(
                    ownerName = details.ownerName,
                    repositoryName = details.repoName
                )

                emit(State.Loaded(githubRepo = details, readmeState = readmeResult.toReadmeState()))
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = State.Loading
        )

    fun setRepoId(id: Int) {
        savedStateHandle[KEY_REPO_ID] = id
    }

    fun onRetryButtonPressed() {
        savedStateHandle[KEY_REPO_ID] = savedStateHandle.get<Int>(KEY_REPO_ID)
    }

    private fun OperationResult.Failure.toUiText(): UiText = when (this) {
        is OperationResult.Failure.NetworkError -> UiText.StringResource(R.string.network_error)
        else -> {
            showLogFowUnknownError()
            UiText.StringResource(R.string.check_your_something)
        }
    }

    private fun OperationResult<String>.toReadmeState(): ReadmeState = when (this) {
        is OperationResult.Success -> {
            if (data.isBlank()) ReadmeState.Empty else ReadmeState.Loaded(data)
        }

        is OperationResult.Failure -> {
            showLogFowUnknownError()
            ReadmeState.Error(this.toUiText())
        }
    }

    private fun OperationResult.Failure.showLogFowUnknownError() {
        if (this is OperationResult.Failure.Unknown) {
            Log.d("RepositoryInfoViewModel", "error message: ${this.message}")
        }
    }

    sealed interface State {
        object Loading : State
        data class Error(val errorUiText: UiText) : State
        data class Loaded(val githubRepo: RepoDetails, val readmeState: ReadmeState) : State
    }

    sealed interface ReadmeState {
        object Loading : ReadmeState
        object Empty : ReadmeState
        data class Error(val errorUiText: UiText) : ReadmeState
        data class Loaded(val markdown: String) : ReadmeState
    }
}

private const val KEY_REPO_ID = "repo_id"