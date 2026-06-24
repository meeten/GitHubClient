package com.example.domain.usecase

import com.example.domain.model.OperationResult
import com.example.domain.model.Repo
import com.example.domain.repository.GitHubRepository

class GetRepoByIdUseCase(
    private val hitHubRepository: GitHubRepository
) {

    suspend operator fun invoke(id: Int): OperationResult<Repo> {
        return hitHubRepository.getRepo(id)
    }
}