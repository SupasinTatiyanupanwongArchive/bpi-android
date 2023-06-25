package dev.supasintatiyanupanwong.apps.android.bpi.currencies.domain.usecases

import dev.supasintatiyanupanwong.apps.android.bpi.currencies.data.CurrenciesRepository

class GetCurrenciesUseCase(private val currenciesRepository: CurrenciesRepository) {

    operator fun invoke() = currenciesRepository.getCurrencies()

}