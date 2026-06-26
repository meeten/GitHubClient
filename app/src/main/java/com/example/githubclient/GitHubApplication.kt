package com.example.githubclient

import android.app.Application
import com.example.authorization.di.authFeatureModule
import com.example.data.di.dataModule
import com.example.domain.di.domainModule
import com.example.githubclient.di.mainModule
import com.example.home.di.homeFeatureModule
import com.example.repo_info.di.repoInfoFeatureModule
import com.example.storage.storageModule
import networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class GitHubApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)

            androidContext(this@GitHubApplication)

            modules(
                networkModule,
                storageModule,
                dataModule,
                domainModule,
                mainModule,
                authFeatureModule,
                homeFeatureModule,
                repoInfoFeatureModule,
            )
        }
    }
}