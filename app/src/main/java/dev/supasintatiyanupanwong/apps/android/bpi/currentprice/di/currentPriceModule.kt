package dev.supasintatiyanupanwong.apps.android.bpi.currentprice.di

import dev.supasintatiyanupanwong.apps.android.bpi.base.data.services.ClientProvider
import dev.supasintatiyanupanwong.apps.android.bpi.base.data.services.CoindeskService
import dev.supasintatiyanupanwong.apps.android.bpi.currentprice.data.CurrentPriceRepository
import dev.supasintatiyanupanwong.apps.android.bpi.currentprice.data.apis.CurrentPriceApi
import dev.supasintatiyanupanwong.apps.android.bpi.currentprice.data.datasources.LocalCurrentPriceDataSource
import dev.supasintatiyanupanwong.apps.android.bpi.currentprice.data.datasources.NetworkCurrentPriceDataSource
import dev.supasintatiyanupanwong.apps.android.bpi.currentprice.data.mappers.CurrentPriceMapper
import org.koin.dsl.module

val currentPriceModule = module {
    single { ClientProvider() } // TODO
    single { CoindeskService(get()) } // TODO

    single { get<CoindeskService>().create(CurrentPriceApi::class.java) }

    single { LocalCurrentPriceDataSource() }
    single { NetworkCurrentPriceDataSource(get()) }

    single { CurrentPriceMapper() }

    single { CurrentPriceRepository(get(), get(), get()) }
}
