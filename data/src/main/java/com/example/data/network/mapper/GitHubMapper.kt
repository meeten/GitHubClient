package com.example.data.network.mapper

import com.example.data.network.model.RepoDto
import com.example.domain.model.Repo
import com.example.domain.model.RepoLang
import com.example.domain.model.RepoLang.JAVA
import com.example.domain.model.RepoLang.KOTLIN
import com.example.domain.model.RepoLang.UNKNOWN

class GitHubMapper {

    fun mapResponseToRepos(response: List<RepoDto>): List<Repo> {
        return response.map { repoDto ->
            Repo(
                id = repoDto.id,
                name = repoDto.name,
                lang = repoDto.language.toRepoLang(),
                description = repoDto.description ?: "",
            )
        }
    }

    private fun String?.toRepoLang(): RepoLang {
        return when (this?.trim()?.uppercase()) {
            KOTLIN.name -> KOTLIN
            JAVA.name -> JAVA
            else -> UNKNOWN
        }
    }
}
