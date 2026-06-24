package com.example.ui.placeholder

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
fun ConnectionErrorScreen(
    modifier: Modifier = Modifier,
    onRefreshButtonPressed: () -> Unit,
) {
    InfoContent(
        src = painterResource(R.drawable.ic_internet),
        title = stringResource(R.string.connection_error),
        description = stringResource(R.string.check_your_internet_connection),
        buttonText = stringResource(R.string.retry),
        isInfoError = true,
        modifier = modifier,
        onClick = onRefreshButtonPressed
    )
}

@Preview(
    name = "connection error",
    showBackground = SHOW_BACKGROUND,
    backgroundColor = BACKGROUND_COLOR
)
@Composable
private fun ConnectionErrorScreenPreview() {
    GitHubClientTheme {
        ConnectionErrorScreen {}
    }
}
