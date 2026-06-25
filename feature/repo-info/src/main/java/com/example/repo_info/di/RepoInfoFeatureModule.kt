package com.example.repo_info.di

import com.example.repo_info.RepositoryInfoViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val repoInfoFeatureModule = module {
    viewModelOf(::RepositoryInfoViewModel)
}