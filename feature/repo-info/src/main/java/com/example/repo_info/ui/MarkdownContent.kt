package com.example.repo_info.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.repo_info.R
import com.example.repo_info.RepositoryInfoViewModel
import com.example.ui.Loading
import com.example.ui.placeholder.ConnectionErrorScreen
import com.example.ui.placeholder.UnknownErrorScreen

@Composable
fun MarkdownContent(
    readmeState: RepositoryInfoViewModel.ReadmeState,
    modifier: Modifier = Modifier,
    onRetryButtonPressed: () -> Unit,
) {
    Column(modifier = modifier) {
        when (readmeState) {
            is RepositoryInfoViewModel.ReadmeState.Loading -> {
                Loading(modifier = Modifier.fillMaxWidth())
            }

            is RepositoryInfoViewModel.ReadmeState.Empty -> {
                Text(
                    text = stringResource(R.string.no_readme_md),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            is RepositoryInfoViewModel.ReadmeState.Loaded -> {
                Text(text = readmeState.markdown)
            }

            is RepositoryInfoViewModel.ReadmeState.Error -> {
                val errorMessage = readmeState.errorUiText.asString()
                when (errorMessage) {
                    stringResource(com.example.ui.R.string.network_error) -> {
                        ConnectionErrorScreen(
                            modifier = Modifier.padding(16.dp),
                            onRefreshButtonPressed = onRetryButtonPressed
                        )
                    }

                    stringResource(com.example.ui.R.string.check_your_something) -> {
                        UnknownErrorScreen(
                            modifier = Modifier.padding(16.dp),
                            onRetryButtonPressed = onRetryButtonPressed
                        )
                    }
                }
            }
        }
    }
}