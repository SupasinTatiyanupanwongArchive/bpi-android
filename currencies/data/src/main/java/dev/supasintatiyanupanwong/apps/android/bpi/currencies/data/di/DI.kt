package dev.supasintatiyanupanwong.apps.android.bpi.currencies.data.di

import dev.supasintatiyanupanwong.apps.android.bpi.currencies.data.CurrenciesRepository
import dev.supasintatiyanupanwong.apps.android.bpi.currencies.data.datasources.CurrenciesDataSource
import dev.supasintatiyanupanwong.apps.android.bpi.currencies.data.datasources.SelectedCurrencyCodeDataSource
import dev.supasintatiyanupanwong.apps.android.bpi.currencies.data.preferences.CurrenciesPreferences
import dev.supasintatiyanupanwong.apps.android.bpi.currencies.domain.CurrenciesRepositoryContract
import org.koin.dsl.module

val currenciesDataModule = module {
    single { CurrenciesDataSource(get(), get()) }
    single { SelectedCurrencyCodeDataSource(get()) }

    single { CurrenciesPreferences(get(), get()) }

    single<CurrenciesRepositoryContract> { CurrenciesRepository(get(), get()) }
}
