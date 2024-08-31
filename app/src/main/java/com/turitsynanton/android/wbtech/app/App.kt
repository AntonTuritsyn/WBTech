package com.turitsynanton.android.wbtech.app

import android.app.Application
import com.turitsynanton.android.ui.BuildConfig
import com.turitsynanton.android.wbtech.data.di.dataModule
import com.turitsynanton.android.wbtech.di.uiModule
import com.turitsynanton.android.wbtech.domain.di.domainModule
import com.yandex.mapkit.MapKitFactory
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        MapKitFactory.setApiKey(BuildConfig.API_KEY)

        startKoin {
            androidLogger(Level.INFO)
            androidContext(this@App)
            modules(
                listOf(
                    uiModule,
                    dataModule,
                    domainModule
                )
            )
        }
    }
}