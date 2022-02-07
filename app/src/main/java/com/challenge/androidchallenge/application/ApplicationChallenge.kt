package com.challenge.androidchallenge.application

import android.app.Application
import com.challenge.androidchallenge.BuildConfig
import com.challenge.androidchallenge.di.viewModelsModule
import com.challenge.androidchallenge.repository.di.networkModule
import com.challenge.androidchallenge.repository.utils.NAME_BASE_URL
import com.challenge.androidchallenge.useCaseModule
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class ApplicationChallenge : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@ApplicationChallenge)
            modules(
                listOf(
                    viewModelsModule, useCaseModule, networkModule
                )
            )
            getKoin().setProperty(NAME_BASE_URL, BuildConfig.BASE_URL)

        }

    }

}