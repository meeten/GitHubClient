package com.example.designsystem

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

private val AppColorScheme = darkColorScheme(
    background = black800,
    surfaceContainerHighest = black800,
    surface = black600,
    primary = blue800,
    outline = grey600,
    secondary = green800,
    error = red600,

    onBackground = Color.White,
    onSurface = Color.White,
    onSurfaceVariant = Color.Gray,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = blue800,
    onError = red400,
)

@Composable
fun GitHubClientTheme(
    content: @Composable () -> Unit
) {
    val customColors = GitHubCustomColors(
        starColor = yellow600,
        forkColor = green600,
        watcherColor = blue600
    )

    CompositionLocalProvider(LocalGitHubColors provides customColors) {
        MaterialTheme(
            colorScheme = AppColorScheme,
            typography = Typography,
            content = content
        )
    }
}