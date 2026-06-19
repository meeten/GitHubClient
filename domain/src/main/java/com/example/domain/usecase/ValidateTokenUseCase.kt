package com.example.domain.usecase

import com.example.domain.model.OperationResult
import com.example.domain.repository.GitHubRepository

class ValidateTokenUseCase(
    private val repository: GitHubRepository
) {

    suspend operator fun invoke(token: String): OperationResult<Unit> {
        return repository.loginWithToken(token)
    }
}