package dev.supasintatiyanupanwong.apps.android.bpi.currencies.domain.di

import dev.supasintatiyanupanwong.apps.android.bpi.currencies.domain.usecases.GetCurrenciesUseCase
import dev.supasintatiyanupanwong.apps.android.bpi.currencies.domain.usecases.GetSelectedCurrencyCodeUseCase
import dev.supasintatiyanupanwong.apps.android.bpi.currencies.domain.usecases.ObserveSelectedCurrencyCodeUseCase
import dev.supasintatiyanupanwong.apps.android.bpi.currencies.domain.usecases.SetSelectedCurrencyCodeUseCase
import org.koin.dsl.module

val currenciesDomainModule = module {
    factory { GetCurrenciesUseCase(get()) }
    factory { GetSelectedCurrencyCodeUseCase(get()) }
    factory { ObserveSelectedCurrencyCodeUseCase(get()) }
    factory { SetSelectedCurrencyCodeUseCase(get()) }
}
