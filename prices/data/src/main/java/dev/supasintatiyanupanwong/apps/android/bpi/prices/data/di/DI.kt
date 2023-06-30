package dev.supasintatiyanupanwong.apps.android.bpi.prices.data.di

import dev.supasintatiyanupanwong.apps.android.bpi.base.data.services.CoindeskService
import dev.supasintatiyanupanwong.apps.android.bpi.prices.data.PricesRepository
import dev.supasintatiyanupanwong.apps.android.bpi.prices.data.apis.CurrentPriceApi
import dev.supasintatiyanupanwong.apps.android.bpi.prices.data.datasources.LocalPricesDataSource
import dev.supasintatiyanupanwong.apps.android.bpi.prices.data.datasources.NetworkCurrentPriceDataSource
import dev.supasintatiyanupanwong.apps.android.bpi.prices.data.mappers.CurrentPriceMapper
import dev.supasintatiyanupanwong.apps.android.bpi.prices.data.storages.PricesPreferencesStorage
import dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.PricesRepositoryContract
import org.koin.dsl.module

val pricesDataModule = module {
    single { get<CoindeskService>().create(CurrentPriceApi::class.java) }

    single { LocalPricesDataSource(get()) }
    single { NetworkCurrentPriceDataSource(get()) }

    single { CurrentPriceMapper(get()) }

    single { PricesPreferencesStorage(get(), get()) }

    single<PricesRepositoryContract> { PricesRepository(get(), get(), get()) }
}
