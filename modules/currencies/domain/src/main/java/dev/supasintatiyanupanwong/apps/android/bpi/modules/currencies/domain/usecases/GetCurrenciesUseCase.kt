package dev.supasintatiyanupanwong.apps.android.bpi.modules.currencies.domain.usecases

import dev.supasintatiyanupanwong.apps.android.bpi.modules.currencies.domain.CurrenciesRepositoryContract

class GetCurrenciesUseCase(
    private val currenciesRepositoryContract: CurrenciesRepositoryContract
) {

    operator fun invoke() = currenciesRepositoryContract.getCurrencies()

}
