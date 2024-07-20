package com.turitsynanton.android.wbtech.app

import android.app.Application
import com.turitsynanton.android.wbtech.di.appModule
import com.turitsynanton.android.wbtech.di.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.INFO)
            androidContext(this@App)
            modules(
                listOf(
                    appModule,
                    dataModule
                )
            )
        }
    }
}