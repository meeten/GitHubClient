package com.example.storage

interface KeyValueStorage {

    fun saveToken(token: String)

    fun getToken(): String?

    fun clearToken()
}