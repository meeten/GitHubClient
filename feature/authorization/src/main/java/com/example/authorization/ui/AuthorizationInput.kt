package com.example.authorization.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.authorization.R

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