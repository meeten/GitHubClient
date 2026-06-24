package com.example.data.network.mapper

import com.example.data.network.model.RepoDto
import com.example.domain.model.Repo

class GitHubMapper {

    fun mapResponseToRepos(response: List<RepoDto>): List<Repo> {
        return response.map { repoDto ->
            Repo(
                id = repoDto.id,
                name = repoDto.name,
                language = repoDto.language,
                description = repoDto.description,
            )
        }
    }
}