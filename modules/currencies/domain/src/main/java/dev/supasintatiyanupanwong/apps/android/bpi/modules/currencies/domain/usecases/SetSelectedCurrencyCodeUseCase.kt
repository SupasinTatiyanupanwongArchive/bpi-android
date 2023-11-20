package dev.supasintatiyanupanwong.apps.android.bpi.modules.currencies.domain.usecases

import dev.supasintatiyanupanwong.apps.android.bpi.modules.currencies.domain.CurrenciesRepositoryContract

class SetSelectedCurrencyCodeUseCase(
    private val currenciesRepositoryContract: CurrenciesRepositoryContract
) {

    operator fun invoke(code: String?) {
        currenciesRepositoryContract.setSelectedCurrencyCode(code)
    }

}
