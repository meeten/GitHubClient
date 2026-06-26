package com.example.domain.usecase

import com.example.domain.model.OperationResult
import com.example.domain.model.RepoDetails
import com.example.domain.repository.GitHubRepository

class GetRepoByIdUseCase(
    private val repository: GitHubRepository
) {

    suspend operator fun invoke(id: Int): OperationResult<RepoDetails> {
        return repository.getRepo(id)
    }
}