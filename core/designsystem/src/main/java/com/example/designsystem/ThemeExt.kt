package com.example.designsystem

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class GitHubCustomColors(
    val starColor: Color,
    val forkColor: Color,
    val watcherColor: Color
)

val LocalGitHubColors = staticCompositionLocalOf {
    GitHubCustomColors(
        starColor = Color.Unspecified,
        forkColor = Color.Unspecified,
        watcherColor = Color.Unspecified
    )
}

val MaterialTheme.gitHubColors: GitHubCustomColors
    @Composable
    get() = LocalGitHubColors.current