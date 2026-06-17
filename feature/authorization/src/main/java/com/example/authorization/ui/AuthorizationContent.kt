package com.example.authorization.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.authorization.R
import com.example.ui.Loading

@Composable
internal fun AuthorizationContent(
    token: String,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    errorReason: String = "",
    onChangeToken: (String) -> Unit = {},
    onSignButtonPressed: () -> Unit = {}
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.ic_group),
            contentDescription = null
        )

        Spacer(modifier = Modifier.height(64.dp))

        AuthorizationInput(
            token = token,
            errorReason = errorReason,
            onChangeToken = onChangeToken
        )


        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onSignButtonPressed,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            )
        ) {
            if (isLoading) {
                Loading(modifier = Modifier.size(24.dp))
            } else {
                Text(text = stringResource(R.string.sign_in))
            }
        }
    }
}