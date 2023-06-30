package dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.di

import dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.usecases.FetchCurrentPriceUseCase
import dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.usecases.FormatPriceUseCase
import dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.usecases.ObserveCurrentPriceOfSelectedCurrencyUseCase
import dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.usecases.ObservePriceRecordsOfSelectedCurrencyUseCase
import dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.usecases.ParsePriceUseCase
import org.koin.dsl.module

val pricesDomainModule = module {
    factory { FetchCurrentPriceUseCase(get()) }
    factory { FormatPriceUseCase(get()) }
    factory { ObserveCurrentPriceOfSelectedCurrencyUseCase(get(), get()) }
    factory { ObservePriceRecordsOfSelectedCurrencyUseCase(get(), get()) }
    factory { ParsePriceUseCase(get()) }
}
