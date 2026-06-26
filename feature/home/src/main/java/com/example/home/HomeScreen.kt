package com.example.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.home.ui.placeholder.EmptyScreen
import com.example.home.ui.HomeContent
import com.example.ui.placeholder.ConnectionErrorScreen
import com.example.ui.placeholder.UnknownErrorScreen
import com.example.ui.Loading
import com.example.ui.R
import com.example.ui.TopAppBarCustom
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel(),
    onLogoutButtonPressed: () -> Unit,
    onRepoClick: (Int, String) -> Unit
) {
    val uiState = viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBarCustom(onClickActionButton = onLogoutButtonPressed)
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .padding(paddingValues)
        ) {
            when (val currentState = uiState.value) {
                HomeViewModel.State.Empty -> {
                    EmptyScreen(
                        modifier = Modifier.padding(16.dp),
                        onRefreshButtonPressed = viewModel::onRefreshButtonPressed
                    )
                }

                HomeViewModel.State.Loading -> {
                    Loading(modifier = Modifier.fillMaxSize())
                }

                is HomeViewModel.State.Loaded -> {
                    HomeContent(
                        repos = currentState.repos,
                        onRepoClick = onRepoClick
                    )
                }

                is HomeViewModel.State.Error -> {
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