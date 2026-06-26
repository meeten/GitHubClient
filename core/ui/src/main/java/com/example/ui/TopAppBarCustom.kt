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
    title: String = stringResource(R.string.repositories),
    actionIcon: Painter = painterResource(R.drawable.ic_exit),
    onNavigationIconClick: (() -> Unit)? = null,
    onActionButtonClick: () -> Unit,
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            if (onNavigationIconClick == null) return@TopAppBar

            IconButton(onClick = onNavigationIconClick) {
                Image(
                    painter = painterResource(R.drawable.ic_back),
                    contentDescription = null
                )
            }
        },
        actions = {
            IconButton(onClick = onActionButtonClick) {
                Image(
                    painter = actionIcon,
                    contentDescription = null
                )
            }
        }
    )
}