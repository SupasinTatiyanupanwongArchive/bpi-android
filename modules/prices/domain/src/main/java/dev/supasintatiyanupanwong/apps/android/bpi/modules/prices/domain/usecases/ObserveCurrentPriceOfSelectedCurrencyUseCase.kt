package dev.supasintatiyanupanwong.apps.android.bpi.modules.prices.domain.usecases

import dev.supasintatiyanupanwong.apps.android.bpi.modules.currencies.domain.usecases.ObserveSelectedCurrencyCodeUseCase
import dev.supasintatiyanupanwong.apps.android.bpi.modules.prices.domain.PricesRepositoryContract
import dev.supasintatiyanupanwong.apps.android.bpi.modules.prices.domain.models.find
import kotlinx.coroutines.flow.combine

class ObserveCurrentPriceOfSelectedCurrencyUseCase(
    private val pricesRepositoryContract: PricesRepositoryContract,
    private val observeSelectedCurrencyCodeUseCase: ObserveSelectedCurrencyCodeUseCase
) {

    operator fun invoke() = pricesRepositoryContract.observeCurrentPrice()
        .combine(observeSelectedCurrencyCodeUseCase()) { current, currencyCode ->
            current?.find { it.currency.currencyCode == currencyCode }
        }

}
