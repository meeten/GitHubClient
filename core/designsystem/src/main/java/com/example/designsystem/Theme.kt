package com.example.designsystem

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val AppColorScheme = darkColorScheme(
    background = black800,
    surfaceContainerHighest = black800,
    surface = black600,
    primary = blue600,
    outline = grey600,
    secondary = green600,
    error = red600,

    onBackground = Color.White,
    onSurface = Color.White,
    onSurfaceVariant = Color.Gray,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = blue600,
    onError = red400,
)

@Composable
fun GitHubClientTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = AppColorScheme,
        typography = Typography,
        content = content
    )
}