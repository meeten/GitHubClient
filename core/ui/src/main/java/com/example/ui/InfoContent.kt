package com.example.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.designsystem.GitHubClientTheme
import com.example.preview.BACKGROUND_COLOR
import com.example.preview.PREVIEW_PADDING
import com.example.preview.SHOW_BACKGROUND

@Composable
fun InfoContent(
    src: Painter,
    title: String,
    description: String,
    buttonText: String,
    modifier: Modifier = Modifier,
    isInfoError: Boolean = false,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = src,
                contentDescription = null
            )

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = title,
                color = if (isInfoError) MaterialTheme.colorScheme.onError else
                    MaterialTheme.colorScheme.onTertiary
            )

            Text(
                text = description,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 14.sp
            )
        }

        Button(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            )
        ) {
            Text(text = buttonText)
        }
    }
}