package com.example.domain.repository

import com.example.domain.model.OperationResult
import com.example.domain.model.Repo
import com.example.domain.model.RepoDetails

interface GitHubRepository {

    suspend fun loginWithToken(token: String): OperationResult<Unit>

    suspend fun getRepos(): OperationResult<List<Repo>>

    suspend fun getRepo(id: Int): OperationResult<RepoDetails>
}