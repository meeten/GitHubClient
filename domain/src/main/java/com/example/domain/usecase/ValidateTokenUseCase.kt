package com.example.domain.usecase

import com.example.domain.repository.GitHubRepository

class ValidateTokenUseCase(
    private val repository: GitHubRepository
) {

    suspend operator fun invoke(token: String): Result<Unit> {
        return repository.checkAuth(token)
    }
}