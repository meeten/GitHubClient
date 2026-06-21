package com.example.data.network.model

import kotlinx.serialization.SerialName

data class RepoDto(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("language") val language: String,
    @SerialName("description") val description: String,
)