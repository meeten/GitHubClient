package com.example.githubclient

import androidx.lifecycle.ViewModel
import com.example.storage.TokenManager

class MainViewModel(
    tokenManager: TokenManager
) : ViewModel() {

    private val hasToken = tokenManager.getToken()

    val action = if (hasToken == null) {
        Action.RouteAuthorization
    } else {
        Action.RouteToHome
    }


    sealed interface Action {

        object RouteAuthorization : Action
        object RouteToHome : Action
    }
}