package com.example.domain.model

data class Repo(
    val id: Int,
    val name: String,
    val lang: RepoLang,
    val description: String,
)
