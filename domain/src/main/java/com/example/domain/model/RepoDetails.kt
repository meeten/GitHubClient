package com.example.domain.model

data class RepoDetails(
    val id: Int,
    val ownerName: String,
    val repoName: String,
    val url: String,
    val licenseName: String,
    val stars: Int,
    val forks: Int,
    val watchers: Int,
)
