package com.example.data.repository

import com.example.data.network.GitHubRemoteDataSource
import com.example.domain.repository.GitHubRepository

class GitHubRepositoryImpl(
    private val remoteDataSource: GitHubRemoteDataSource
) : GitHubRepository {

    override suspend fun checkAuth(token: String): Result<Unit> {
        return remoteDataSource.checkAuth(token)
    }
}