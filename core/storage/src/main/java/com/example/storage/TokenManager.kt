package com.example.storage

interface TokenManager {

    fun saveToken(token: String)

    fun getToken(): String?

    fun clearToken()
}