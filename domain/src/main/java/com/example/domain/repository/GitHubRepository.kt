package com.example.domain.repository

interface GitHubRepository {

    suspend fun checkAuth(token: String): Result<Unit>
}