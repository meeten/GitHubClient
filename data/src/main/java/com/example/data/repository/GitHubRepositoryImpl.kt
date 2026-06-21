package com.example.data.repository

import com.example.data.network.GitHubRemoteDataSource
import com.example.domain.model.OperationResult
import com.example.domain.model.Repo
import com.example.domain.repository.GitHubRepository
import com.example.storage.TokenManager

class GitHubRepositoryImpl(
    private val remoteDataSource: GitHubRemoteDataSource,
    private val tokenManager: TokenManager
) : GitHubRepository {

    override suspend fun loginWithToken(token: String): OperationResult<Unit> {
        return remoteDataSource.checkAuth(token).also {
            saveToken(token = token, operationResult = it)
        }
    }

    override suspend fun getRepos(): OperationResult<List<Repo>> {
        val token = tokenManager.getToken() ?: return OperationResult.Failure.InvalidToken
        return remoteDataSource.getRepos(token)
    }

    private fun saveToken(
        token: String,
        operationResult: OperationResult<Unit>,
    ) {
        when (operationResult) {
            is OperationResult.Success<Unit> -> {
                tokenManager.saveToken(token)
            }

            else -> return
        }
    }
}