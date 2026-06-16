package com.example.data.repository

import com.example.data.network.GitHubRemoteDataSource
import com.example.domain.model.OperationResult
import com.example.domain.repository.GitHubRepository

class GitHubRepositoryImpl(
    private val remoteDataSource: GitHubRemoteDataSource
) : GitHubRepository {

    override suspend fun checkAuth(token: String): OperationResult<Unit> {
        return remoteDataSource.checkAuth(token)
    }
}