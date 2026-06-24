package com.example.ui

import androidx.compose.foundation.Image
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarCustom(
    title:String = stringResource(R.string.repositories),
    actionIcon: Painter = painterResource(R.drawable.ic_exit),
    onClickActionButton: () -> Unit,
) {
    TopAppBar(
        title = { Text(text = title) },
        actions = {
            IconButton(onClick = onClickActionButton) {
                Image(
                    painter = actionIcon,
                    contentDescription = null
                )
            }
        }
    )
}