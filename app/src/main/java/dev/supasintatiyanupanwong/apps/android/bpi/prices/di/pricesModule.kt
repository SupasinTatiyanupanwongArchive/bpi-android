package dev.supasintatiyanupanwong.apps.android.bpi.prices.di

import com.google.gson.Gson
import dev.supasintatiyanupanwong.apps.android.bpi.base.data.services.ClientProvider
import dev.supasintatiyanupanwong.apps.android.bpi.base.data.services.CoindeskService
import dev.supasintatiyanupanwong.apps.android.bpi.prices.data.PricesRepository
import dev.supasintatiyanupanwong.apps.android.bpi.prices.data.apis.CurrentPriceApi
import dev.supasintatiyanupanwong.apps.android.bpi.prices.data.datasources.LocalPricesDataSource
import dev.supasintatiyanupanwong.apps.android.bpi.prices.data.datasources.NetworkCurrentPriceDataSource
import dev.supasintatiyanupanwong.apps.android.bpi.prices.data.mappers.CurrentPriceMapper
import dev.supasintatiyanupanwong.apps.android.bpi.prices.data.storages.PricesPreferencesStorage
import dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.usecases.FetchCurrentPriceUseCase
import dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.usecases.FormatPriceUseCase
import dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.usecases.ObserveCurrentPriceOfSelectedCurrencyUseCase
import dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.usecases.ParsePriceUseCase
import dev.supasintatiyanupanwong.apps.android.bpi.prices.ui.PricesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val pricesModule = module {
    single { Gson() } // TODO
    single { ClientProvider() } // TODO
    single { CoindeskService(get()) } // TODO

    single { get<CoindeskService>().create(CurrentPriceApi::class.java) }

    single { LocalPricesDataSource(get()) }
    single { NetworkCurrentPriceDataSource(get()) }

    single { CurrentPriceMapper(get()) }

    single { PricesPreferencesStorage(get(), get()) }

    single { PricesRepository(get(), get(), get()) }

    factory { FetchCurrentPriceUseCase(get()) }
    factory { FormatPriceUseCase(get()) }
    factory { ObserveCurrentPriceOfSelectedCurrencyUseCase(get(), get()) }
    factory { ParsePriceUseCase(get()) }

    viewModel { PricesViewModel(get(), get(), get()) }
}
