package com.example.githubclient.navigation

internal sealed class Screen(val route: String) {

    object Authorization : Screen(route = AUTHORIZATION_ROUTE)

    object Home : Screen(route = HOME_ROUTE)

    companion object {
        private const val AUTHORIZATION_ROUTE = "authorization"

        private const val HOME_ROUTE = "home"
    }
}