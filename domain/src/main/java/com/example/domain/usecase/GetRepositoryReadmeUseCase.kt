package com.example.domain.usecase

import com.example.domain.model.OperationResult
import com.example.domain.repository.GitHubRepository

class GetRepositoryReadmeUseCase(
    private val repository: GitHubRepository
) {

    suspend operator fun invoke(
        ownerName: String,
        repositoryName: String
    ): OperationResult<String> {
        return repository.getRepositoryReadme(ownerName, repositoryName)
    }
}