package dev.supasintatiyanupanwong.apps.android.bpi.currencies.domain.usecases

import dev.supasintatiyanupanwong.apps.android.bpi.currencies.data.CurrenciesRepository

class GetSelectedCurrencyCodeUseCase(private val currenciesRepository: CurrenciesRepository) {

    operator fun invoke() = currenciesRepository.getSelectedCurrencyCode()

}
