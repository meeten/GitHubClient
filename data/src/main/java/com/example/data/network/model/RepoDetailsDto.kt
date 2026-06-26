package com.example.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RepoDetailsDto(
    @SerialName("id") val id: Int,
    @SerialName("owner") val owner: OwnerDto,
    @SerialName("name") val name: String,
    @SerialName("html_url") val url: String,
    @SerialName("license") val license: LicenseDto?,
    @SerialName("stargazers_count") val stars: Int,
    @SerialName("forks_count") val forks: Int,
    @SerialName("watchers_count") val watchers: Int,
)
