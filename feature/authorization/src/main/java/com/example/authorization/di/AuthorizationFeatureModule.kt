package com.example.authorization.di

import com.example.authorization.AuthorizationViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val authFeatureModule = module {
    viewModelOf(::AuthorizationViewModel)
}