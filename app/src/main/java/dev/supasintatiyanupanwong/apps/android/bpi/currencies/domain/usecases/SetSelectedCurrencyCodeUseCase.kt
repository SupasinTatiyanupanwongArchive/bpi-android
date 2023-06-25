package dev.supasintatiyanupanwong.apps.android.bpi.currencies.domain.usecases

import dev.supasintatiyanupanwong.apps.android.bpi.currencies.data.CurrenciesRepository

class SetSelectedCurrencyCodeUseCase(private val currenciesRepository: CurrenciesRepository) {

    operator fun invoke(code: String?) {
        currenciesRepository.setSelectedCurrencyCode(code)
    }

}
