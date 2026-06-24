package com.example.home.ui.placeholder

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.designsystem.GitHubClientTheme
import com.example.preview.BACKGROUND_COLOR
import com.example.preview.SHOW_BACKGROUND
import com.example.ui.InfoContent
import com.example.ui.R

@Composable
fun EmptyScreen(
    modifier: Modifier = Modifier,
    onRefreshButtonPressed: () -> Unit,
) {
    InfoContent(
        src = painterResource(R.drawable.ic_empty),
        title = stringResource(R.string.empty),
        description = stringResource(R.string.no_repositories_at_the_moment),
        buttonText = stringResource(R.string.refresh),
        modifier = modifier,
        onClick = onRefreshButtonPressed
    )
}


@Preview(
    name = "empty",
    showBackground = SHOW_BACKGROUND,
    backgroundColor = BACKGROUND_COLOR
)
@Composable
private fun EmptyScreenPreview() {
    GitHubClientTheme {
        EmptyScreen { }
    }
}