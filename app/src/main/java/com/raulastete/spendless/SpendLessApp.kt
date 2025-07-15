package com.raulastete.spendless

import android.app.Application
import com.raulastete.spendless.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SpendLessApp : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidLogger()
            androidContext(this@SpendLessApp)
            modules(
                listOf(
                    viewModelModule,
                )
            )
        }
    }

}