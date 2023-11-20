package dev.supasintatiyanupanwong.apps.android.bpi.app

import android.app.Application
import dev.supasintatiyanupanwong.apps.android.bpi.base.di.baseModule
import dev.supasintatiyanupanwong.apps.android.bpi.modules.math.di.mathModule
import dev.supasintatiyanupanwong.apps.android.bpi.modules.currencies.di.currenciesModule
import dev.supasintatiyanupanwong.apps.android.bpi.modules.prices.di.pricesModule
import dev.supasintatiyanupanwong.apps.android.bpi.modules.time.di.timeModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@Application)
            modules(
                baseModule,
                currenciesModule,
                mathModule,
                pricesModule,
                timeModule
            )
        }
    }

}
