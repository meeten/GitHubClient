package com.example.data.repository

import com.example.data.network.GitHubRemoteDataSource
import com.example.domain.model.OperationResult
import com.example.domain.model.Repo
import com.example.domain.model.RepoDetails
import com.example.domain.repository.GitHubRepository
import com.example.storage.KeyValueStorage

class GitHubRepositoryImpl(
    private val remoteDataSource: GitHubRemoteDataSource,
    private val tokenManager: KeyValueStorage
) : GitHubRepository {

    private var token: String = tokenManager.getToken() ?: ""

    override suspend fun loginWithToken(token: String): OperationResult<Unit> {
        return remoteDataSource.checkAuth(token).also {
            saveToken(token = token, operationResult = it)
        }
    }

    override suspend fun getRepos(): OperationResult<List<Repo>> {
        return remoteDataSource.getRepos(token)
    }

    override suspend fun getRepo(id: Int): OperationResult<RepoDetails> {
        return remoteDataSource.getRepo(token = token, repoId = id)
    }

    override suspend fun getRepositoryReadme(
        ownerName: String,
        repositoryName: String
    ): OperationResult<String> {
        return remoteDataSource.getRepositoryReadme(
            token = token,
            ownerName = ownerName,
            repositoryName = repositoryName
        )
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