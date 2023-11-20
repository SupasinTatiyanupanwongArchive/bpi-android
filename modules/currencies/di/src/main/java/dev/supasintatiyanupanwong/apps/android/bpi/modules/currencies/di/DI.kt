package dev.supasintatiyanupanwong.apps.android.bpi.modules.currencies.di

import dev.supasintatiyanupanwong.apps.android.bpi.modules.currencies.data.CurrenciesRepository
import dev.supasintatiyanupanwong.apps.android.bpi.modules.currencies.data.datasources.CurrenciesDataSource
import dev.supasintatiyanupanwong.apps.android.bpi.modules.currencies.data.datasources.SelectedCurrencyCodeDataSource
import dev.supasintatiyanupanwong.apps.android.bpi.modules.currencies.data.preferences.CurrenciesPreferences
import dev.supasintatiyanupanwong.apps.android.bpi.modules.currencies.domain.CurrenciesRepositoryContract
import dev.supasintatiyanupanwong.apps.android.bpi.modules.currencies.domain.usecases.GetCurrenciesUseCase
import dev.supasintatiyanupanwong.apps.android.bpi.modules.currencies.domain.usecases.GetSelectedCurrencyCodeUseCase
import dev.supasintatiyanupanwong.apps.android.bpi.modules.currencies.domain.usecases.ObserveSelectedCurrencyCodeUseCase
import dev.supasintatiyanupanwong.apps.android.bpi.modules.currencies.domain.usecases.SetSelectedCurrencyCodeUseCase
import org.koin.dsl.module

val currenciesModule = module {
    single { CurrenciesDataSource(get(), get()) }
    single { SelectedCurrencyCodeDataSource(get()) }

    single { CurrenciesPreferences(get(), get()) }

    single<CurrenciesRepositoryContract> { CurrenciesRepository(get(), get()) }

    factory { GetCurrenciesUseCase(get()) }
    factory { GetSelectedCurrencyCodeUseCase(get()) }
    factory { ObserveSelectedCurrencyCodeUseCase(get()) }
    factory { SetSelectedCurrencyCodeUseCase(get()) }
}
