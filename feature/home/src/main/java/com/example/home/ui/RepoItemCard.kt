package com.example.home.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.designsystem.GitHubClientTheme
import com.example.preview.BACKGROUND_COLOR
import com.example.preview.SHOW_BACKGROUND

@Composable
fun RepoItemCard(
    name: String,
    description: String,
    lang: String,
    langColor: Color,
    modifier: Modifier = Modifier,
    isLastItem: Boolean = false,
    ) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 24.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = name,
                    color = MaterialTheme.colorScheme.onTertiary,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = lang,
                    color = langColor,
                    fontSize = 12.sp
                )
            }

            if (description.isNotBlank()) {
                Text(
                    text = description,
                    fontSize = 14.sp
                )
            }
        }

        if (!isLastItem){
            HorizontalDivider(
                modifier = Modifier.align(Alignment.BottomCenter),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.outline
            )
        }
    }
}

@Preview(
    showBackground = SHOW_BACKGROUND,
    backgroundColor = BACKGROUND_COLOR
)
@Composable
fun RepoItemCardWithDescriptionPreview() {
    GitHubClientTheme {
        RepoItemCard(
            name = "git hub client",
            lang = "kotlin",
            description = "It's git hub client",
            langColor = Color(0xFFA37AEE),
        )
    }
}

@Preview(
    showBackground = SHOW_BACKGROUND,
    backgroundColor = BACKGROUND_COLOR
)
@Composable
fun RepoItemCardWithoutDescriptionPreview() {
    GitHubClientTheme {
        RepoItemCard(
            name = "git hub client",
            lang = "kotlin",
            description = "",
            langColor = Color(0xFFA37AEE),
        )
    }
}