package dev.supasintatiyanupanwong.apps.android.bpi.currencies.di

import dev.supasintatiyanupanwong.apps.android.bpi.currencies.data.CurrenciesRepository
import dev.supasintatiyanupanwong.apps.android.bpi.currencies.data.datasources.CurrenciesDataSource
import dev.supasintatiyanupanwong.apps.android.bpi.currencies.data.datasources.SelectedCurrencyCodeDataSource
import dev.supasintatiyanupanwong.apps.android.bpi.currencies.data.preferences.CurrenciesPreferences
import dev.supasintatiyanupanwong.apps.android.bpi.currencies.domain.usecases.GetCurrenciesUseCase
import dev.supasintatiyanupanwong.apps.android.bpi.currencies.domain.usecases.GetSelectedCurrencyCodeUseCase
import dev.supasintatiyanupanwong.apps.android.bpi.currencies.domain.usecases.ObserveSelectedCurrencyCodeUseCase
import dev.supasintatiyanupanwong.apps.android.bpi.currencies.domain.usecases.SetSelectedCurrencyCodeUseCase
import org.koin.dsl.module

val currenciesModule = module {
    single { CurrenciesDataSource(get(), get()) }
    single { SelectedCurrencyCodeDataSource(get()) }

    single { CurrenciesPreferences(get(), get()) }

    single { CurrenciesRepository(get(), get()) }

    factory { GetCurrenciesUseCase(get()) }
    factory { GetSelectedCurrencyCodeUseCase(get()) }
    factory { ObserveSelectedCurrencyCodeUseCase(get()) }
    factory { SetSelectedCurrencyCodeUseCase(get()) }
}
