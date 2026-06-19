package com.example.githubclient.di

import com.example.githubclient.MainViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val mainModule = module {
    viewModelOf(::MainViewModel)
}