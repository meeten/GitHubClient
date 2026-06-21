package com.example.domain.usecase

import com.example.domain.model.OperationResult
import com.example.domain.model.Repo
import com.example.domain.repository.GitHubRepository

class GetReposUseCase(
    private val repository: GitHubRepository
) {

    suspend operator fun invoke(): OperationResult<List<Repo>> {
        return repository.getRepos()
    }
}