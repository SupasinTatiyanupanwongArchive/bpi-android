package dev.supasintatiyanupanwong.apps.android.bpi.currencies.domain.usecases

import dev.supasintatiyanupanwong.apps.android.bpi.currencies.domain.CurrenciesRepositoryContract

class GetCurrenciesUseCase(
    private val currenciesRepositoryContract: CurrenciesRepositoryContract
) {

    operator fun invoke() = currenciesRepositoryContract.getCurrencies()

}
