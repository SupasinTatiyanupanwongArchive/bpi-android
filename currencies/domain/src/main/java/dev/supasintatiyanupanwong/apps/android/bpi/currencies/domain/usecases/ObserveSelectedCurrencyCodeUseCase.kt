package dev.supasintatiyanupanwong.apps.android.bpi.currencies.domain.usecases

import dev.supasintatiyanupanwong.apps.android.bpi.currencies.domain.CurrenciesRepositoryContract

class ObserveSelectedCurrencyCodeUseCase(
    private val currenciesRepositoryContract: CurrenciesRepositoryContract
) {

    operator fun invoke() = currenciesRepositoryContract.observeSelectedCurrencyCode()

}
