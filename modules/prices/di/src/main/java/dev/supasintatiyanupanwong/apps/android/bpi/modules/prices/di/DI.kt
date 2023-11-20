package dev.supasintatiyanupanwong.apps.android.bpi.modules.prices.di

import dev.supasintatiyanupanwong.apps.android.bpi.base.data.services.CoindeskService
import dev.supasintatiyanupanwong.apps.android.bpi.modules.prices.data.PricesRepository
import dev.supasintatiyanupanwong.apps.android.bpi.modules.prices.data.apis.CurrentPriceApi
import dev.supasintatiyanupanwong.apps.android.bpi.modules.prices.data.datasources.LocalPricesDataSource
import dev.supasintatiyanupanwong.apps.android.bpi.modules.prices.data.datasources.NetworkCurrentPriceDataSource
import dev.supasintatiyanupanwong.apps.android.bpi.modules.prices.data.mappers.CurrentPriceMapper
import dev.supasintatiyanupanwong.apps.android.bpi.modules.prices.data.storages.PricesPreferencesStorage
import dev.supasintatiyanupanwong.apps.android.bpi.modules.prices.domain.PricesRepositoryContract
import dev.supasintatiyanupanwong.apps.android.bpi.modules.prices.domain.usecases.FetchCurrentPriceUseCase
import dev.supasintatiyanupanwong.apps.android.bpi.modules.prices.domain.usecases.FormatPriceUseCase
import dev.supasintatiyanupanwong.apps.android.bpi.modules.prices.domain.usecases.ObserveCurrentPriceOfSelectedCurrencyUseCase
import dev.supasintatiyanupanwong.apps.android.bpi.modules.prices.domain.usecases.ObservePriceRecordsOfSelectedCurrencyUseCase
import dev.supasintatiyanupanwong.apps.android.bpi.modules.prices.domain.usecases.ParsePriceUseCase
import dev.supasintatiyanupanwong.apps.android.bpi.modules.prices.ui.screens.PricesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val pricesModule = module {
    single { get<CoindeskService>().create(CurrentPriceApi::class.java) }

    single { LocalPricesDataSource(get()) }
    single { NetworkCurrentPriceDataSource(get()) }

    single { CurrentPriceMapper(get()) }

    single { PricesPreferencesStorage(get(), get()) }

    single<PricesRepositoryContract> { PricesRepository(get(), get(), get()) }

    factory { FetchCurrentPriceUseCase(get()) }
    factory { FormatPriceUseCase(get()) }
    factory { ObserveCurrentPriceOfSelectedCurrencyUseCase(get(), get()) }
    factory { ObservePriceRecordsOfSelectedCurrencyUseCase(get(), get()) }
    factory { ParsePriceUseCase(get()) }

    viewModel { PricesViewModel(get(), get(), get(), get(), get()) }
}
