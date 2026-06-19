package com.example.data.network

import com.example.data.extension.safeNetworkCall
import com.example.domain.model.OperationResult
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders

class GitHubRemoteDataSource(
    private val networkClient: HttpClient
) {

    suspend fun checkAuth(token: String): OperationResult<Unit> {
        return safeNetworkCall(
            call = {
                networkClient.get("octocat") {
                    headers {
                        append(name = HttpHeaders.Authorization, value = "token $token")
                    }
                }
            },
            transform = { }
        )
    }
}
