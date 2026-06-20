package com.example.githubclient

import androidx.lifecycle.ViewModel
import com.example.githubclient.navigation.Screen
import com.example.storage.TokenManager

class MainViewModel(
    private val tokenManager: TokenManager
) : ViewModel() {

    fun getStartDestination(): String {
        val token = tokenManager.getToken()

        return when (token) {
            null ->  Screen.Authorization.route
            else -> Screen.Home.route
        }
    }
}