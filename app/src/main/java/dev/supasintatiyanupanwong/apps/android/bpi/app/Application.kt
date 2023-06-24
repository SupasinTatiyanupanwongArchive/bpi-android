package dev.supasintatiyanupanwong.apps.android.bpi.app

import android.app.Application
import dev.supasintatiyanupanwong.apps.android.bpi.currentprice.di.currentPriceModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@Application)
            modules(currentPriceModule)
        }
    }

}
