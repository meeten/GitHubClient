package com.example.domain.di

import com.example.domain.usecase.ValidateTokenUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { ValidateTokenUseCase(repository = get()) }
}