package com.example.githubclient.navigation

internal sealed class Screen(val route: String) {

    object Authorization : Screen(route = AUTHORIZATION_ROUTE)

    object Home : Screen(route = HOME_ROUTE)

    object RepoInfo : Screen(route = REPO_INFO_ROUTE) {

        private const val REPO_INFO_ROUTE_WITH_ARGS = "repo_info"
        fun createRouteWithArgs(repoId: Int, repoName: String): String {
            return "$REPO_INFO_ROUTE_WITH_ARGS/$repoId/$repoName"
        }
    }

    companion object {
        const val KEY_REPO_ID = "repo_id"
        const val KEY_PEPO_NAME = "repo_name"

        private const val AUTHORIZATION_ROUTE = "authorization"
        private const val HOME_ROUTE = "home"
        private const val REPO_INFO_ROUTE = "repo_info/{$KEY_REPO_ID}/{$KEY_PEPO_NAME}"
    }
}