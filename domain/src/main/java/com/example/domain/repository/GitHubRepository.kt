package com.example.domain.repository

import com.example.domain.model.OperationResult

interface GitHubRepository {

    suspend fun loginWithToken(token: String): OperationResult<Unit>
}