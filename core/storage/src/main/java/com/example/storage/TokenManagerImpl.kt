package com.example.storage

import android.content.Context
import androidx.core.content.edit

class TokenManagerImpl(context: Context) : TokenManager {

    private val sharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    override fun saveToken(token: String) {
        sharedPreferences.edit {
            putString(KEY_TOKEN, token)
        }
    }

    override fun getToken(): String? {
        val token = sharedPreferences.getString(KEY_TOKEN, null)
        return token
    }

    override fun clearToken() {
        sharedPreferences.edit { clear() }
    }

    companion object {

        private const val PREFS_NAME = "github_prefs"
        private const val KEY_TOKEN = "github_auth_token"
    }
}