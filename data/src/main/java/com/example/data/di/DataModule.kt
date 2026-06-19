package com.example.data.di

import com.example.data.network.GitHubRemoteDataSource
import com.example.data.repository.GitHubRepositoryImpl
import com.example.domain.repository.GitHubRepository
import org.koin.dsl.module

val dataModule = module {
    single { GitHubRemoteDataSource(networkClient = get()) }

    single<GitHubRepository> {
        GitHubRepositoryImpl(
            remoteDataSource = get(),
            tokenManager = get()
        )
    }
}