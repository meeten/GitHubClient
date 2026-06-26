package com.example.authorization.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.authorization.R
import com.example.designsystem.GitHubClientTheme
import com.example.preview.BACKGROUND_COLOR
import com.example.preview.SHOW_BACKGROUND

@Composable
fun AuthorizationInput(
    token: String,
    errorReason: String,
    modifier: Modifier = Modifier,
    onChangeToken: (String) -> Unit
) {
    val isErrorInput = errorReason.isNotBlank()

    TextField(
        value = token,
        modifier = modifier.fillMaxWidth(),
        onValueChange = {
            onChangeToken(it)
        },
        isError = isErrorInput,
        label = {
            Text(text = stringResource(R.string.personal_access_token))
        },
        supportingText = {
            if (isErrorInput) {
                Text(text = errorReason)
            }
        },
        colors = TextFieldDefaults.colors(
            unfocusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    )
}

@Preview(
    name = "empty",
    showBackground = SHOW_BACKGROUND,
    backgroundColor = BACKGROUND_COLOR
)
@Composable
private fun AuthorizationInputEmptyPreview() {
    GitHubClientTheme {
        AuthorizationInput(
            token = "",
            errorReason = ""
        ) { }
    }
}

@Preview(
    name = "error",
    showBackground = SHOW_BACKGROUND,
    backgroundColor = BACKGROUND_COLOR
)
@Composable
private fun AuthorizationInputErrorPreview() {
    GitHubClientTheme {
        AuthorizationInput(
            token = "",
            errorReason = "Invalid token"
        ) { }
    }
}