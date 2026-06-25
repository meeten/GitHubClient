package com.example.repo_info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.repo_info.ui.RepositoryInfoContent
import com.example.ui.Loading
import com.example.ui.R
import com.example.ui.TopAppBarCustom
import com.example.ui.placeholder.ConnectionErrorScreen
import com.example.ui.placeholder.UnknownErrorScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun RepositoryInfoScreen(
    repoId: Int,
    repoName: String,
    modifier: Modifier = Modifier,
    viewModel: RepositoryInfoViewModel = koinViewModel(),
) {
    LaunchedEffect(repoId) {
        viewModel.setRepoId(id = repoId)
    }

    val uiState = viewModel.uiState.collectAsState()


    Scaffold(
        topBar = {
            TopAppBarCustom(title = repoName) { }
        }
    ) { paddingValues ->
        Column(modifier = modifier.padding(paddingValues)) {
            when (val currentState = uiState.value) {
                RepositoryInfoViewModel.State.Loading -> {
                    Loading()
                }

                is RepositoryInfoViewModel.State.Loaded -> {
                    RepositoryInfoContent(
                        url = currentState.githubRepo.url,
                        licenseName = currentState.githubRepo.licenseName,
                        stars = currentState.githubRepo.stars,
                        forks = currentState.githubRepo.forks,
                        watchers = currentState.githubRepo.watchers,
                        readmeState = currentState.readmeState,
                        onRetryButtonPressed = viewModel::onRetryButtonPressed
                    )
                }

                is RepositoryInfoViewModel.State.Error -> {
                    val errorMessage = currentState.errorUiText.asString()
                    when (errorMessage) {
                        stringResource(R.string.network_error) -> {
                            ConnectionErrorScreen(
                                modifier = Modifier.padding(16.dp),
                                onRefreshButtonPressed = viewModel::onRetryButtonPressed
                            )
                        }

                        stringResource(R.string.check_your_something) -> {
                            UnknownErrorScreen(
                                modifier = Modifier.padding(16.dp),
                                onRetryButtonPressed = viewModel::onRetryButtonPressed
                            )
                        }
                    }
                }
            }
        }
    }
}