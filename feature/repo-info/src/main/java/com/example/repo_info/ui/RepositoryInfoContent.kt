package com.example.repo_info.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.designsystem.GitHubClientTheme
import com.example.designsystem.gitHubColors
import com.example.preview.BACKGROUND_COLOR
import com.example.preview.SHOW_BACKGROUND
import com.example.repo_info.R
import com.example.repo_info.RepositoryInfoViewModel

@Composable
fun RepositoryInfoContent(
    url: String,
    licenseName: String,
    stars: Int,
    forks: Int,
    watchers: Int,
    readmeState: RepositoryInfoViewModel.ReadmeState,
    modifier: Modifier = Modifier,
    onRetryButtonPressed: () -> Unit
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(state = scrollState),
    ) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            UrlContent(url)

            LicenseContent(licenseName)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                StatisticItemContent(
                    painter = painterResource(R.drawable.ic_star),
                    accentColor = MaterialTheme.gitHubColors.starColor,
                    value = stars.toString(),
                    title = stringResource(R.string.stars)
                )

                StatisticItemContent(
                    painter = painterResource(R.drawable.ic_tree),
                    accentColor = MaterialTheme.gitHubColors.forkColor,
                    value = forks.toString(),
                    title = stringResource(R.string.forks)
                )

                StatisticItemContent(
                    painter = painterResource(R.drawable.ic_eye),
                    accentColor = MaterialTheme.gitHubColors.watcherColor,
                    value = watchers.toString(),
                    title = stringResource(R.string.watchers)
                )
            }

        }

        Spacer(modifier = Modifier.height(32.dp))

        MarkdownContent(
            readmeState = readmeState,
            onRetryButtonPressed = onRetryButtonPressed
        )
    }
}

@Composable
fun UrlContent(
    url: String,
    modifier: Modifier = Modifier
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(R.drawable.ic_link),
            contentDescription = null
        )

        Spacer(modifier = modifier.width(8.dp))

        Text(
            text = url,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onTertiary
        )
    }
}

@Composable
private fun LicenseContent(
    licenseName: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(R.drawable.ic_balance),
            contentDescription = null
        )

        Spacer(modifier = modifier.width(16.dp))

        Text(
            text = stringResource(R.string.license),
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = licenseName,
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = 16.sp
        )
    }
}

@Composable
private fun StatisticItemContent(
    painter: Painter,
    accentColor: Color,
    value: String,
    title: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painter,
            contentDescription = null,
            tint = accentColor
        )

        Text(
            text = value,
            color = accentColor,
            fontSize = 16.sp
        )

        Text(
            text = title,
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = 16.sp
        )
    }
}

@Preview(
    showBackground = SHOW_BACKGROUND,
    backgroundColor = BACKGROUND_COLOR
)
@Composable
fun RepositoryInfoContentPreview() {
    GitHubClientTheme {
        RepositoryInfoContent(
            url = "github.com/icerockdev/moko-resources",
            licenseName = "Apache 2.0",
            stars = 259,
            forks = 30,
            watchers = 10,
            readmeState = RepositoryInfoViewModel.ReadmeState.Empty,
            modifier = Modifier.padding(8.dp)
        ) {}
    }
}