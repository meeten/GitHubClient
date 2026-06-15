package com.example.data.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders

class GitHubRemoteDataSource(
    private val networkClient: HttpClient
) {

    suspend fun checkAuth(token: String): Result<Unit> {
        return runCatching {
            networkClient.get("octocat") {
                headers {
                    append(name = HttpHeaders.Authorization, value = "token $token")
                }
            }
        }
    }
}