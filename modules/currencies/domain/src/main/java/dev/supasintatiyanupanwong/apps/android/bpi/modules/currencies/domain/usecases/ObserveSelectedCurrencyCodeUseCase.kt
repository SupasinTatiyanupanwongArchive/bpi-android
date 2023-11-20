package dev.supasintatiyanupanwong.apps.android.bpi.modules.currencies.domain.usecases

import dev.supasintatiyanupanwong.apps.android.bpi.modules.currencies.domain.CurrenciesRepositoryContract

class ObserveSelectedCurrencyCodeUseCase(
    private val currenciesRepositoryContract: CurrenciesRepositoryContract
) {

    operator fun invoke() = currenciesRepositoryContract.observeSelectedCurrencyCode()

}
