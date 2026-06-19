package com.example.storage

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val storageModule = module {
    single<TokenManager> { TokenManagerImpl(context = androidContext()) }
}