package dev.supasintatiyanupanwong.apps.android.bpi.app

import android.app.Application
import dev.supasintatiyanupanwong.apps.android.bpi.base.data.di.baseDataModule
import dev.supasintatiyanupanwong.apps.android.bpi.currencies.data.di.currenciesDataModule
import dev.supasintatiyanupanwong.apps.android.bpi.currencies.domain.di.currenciesDomainModule
import dev.supasintatiyanupanwong.apps.android.bpi.math.domain.di.mathDomainModule
import dev.supasintatiyanupanwong.apps.android.bpi.prices.data.di.pricesDataModule
import dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.di.pricesDomainModule
import dev.supasintatiyanupanwong.apps.android.bpi.prices.ui.di.pricesUiModule
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
                baseDataModule,
                currenciesDataModule,
                currenciesDomainModule,
                mathDomainModule,
                pricesDataModule,
                pricesDomainModule,
                pricesUiModule
            )
        }
    }

}
