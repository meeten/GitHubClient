package com.example.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class RepoDto(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("language") val language: String?,
    @SerialName("description") val description: String?,
)