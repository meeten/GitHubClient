package com.example.designsystem

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val AppColorScheme = darkColorScheme(
    background = Color.Black,
    surfaceContainerHighest = Color.Black,
    primary = blue600,
    secondary = green600,
    error = red600,

    onBackground = Color.White,
    onSurface = Color.White,
    onSurfaceVariant = Color.Gray,
    onPrimary = Color.White,
    onSecondary = Color.White
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