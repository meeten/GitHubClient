package com.example.data.network

import com.example.data.extension.safeNetworkCall
import com.example.data.network.mapper.GitHubMapper
import com.example.data.network.model.RepoDto
import com.example.domain.model.OperationResult
import com.example.domain.model.Repo
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders

class GitHubRemoteDataSource(
    private val networkClient: HttpClient,
    private val mapper: GitHubMapper
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

    suspend fun getRepos(token: String): OperationResult<List<Repo>> {
        return safeNetworkCall(
            call = {
                networkClient.get("user/repos") {
                    headers {
                        append(name = HttpHeaders.Accept, value = "application/vnd.github+json")
                        append(name = HttpHeaders.Authorization, value = "Bearer $token")
                    }
                }
            },
            transform = { response ->
                val reposDto = response.body<List<RepoDto>>()
                mapper.responseToRepos(reposDto)
            }
        )
    }
}
