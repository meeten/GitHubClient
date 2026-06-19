package com.example.data.repository

import com.example.data.network.GitHubRemoteDataSource
import com.example.domain.model.OperationResult
import com.example.domain.repository.GitHubRepository
import com.example.storage.TokenManager

class GitHubRepositoryImpl(
    private val remoteDataSource: GitHubRemoteDataSource,
    private val tokenManager: TokenManager
) : GitHubRepository {

    override suspend fun loginWithToken(token: String): OperationResult<Unit> {
        return remoteDataSource.checkAuth(token).also {
            it.saveToken(token)
        }
    }

    private fun OperationResult<Unit>.saveToken(token: String) {
        when (this) {
            is OperationResult.Success<Unit> -> {
                tokenManager.saveToken(token)
            }

            else -> return
        }
    }
}