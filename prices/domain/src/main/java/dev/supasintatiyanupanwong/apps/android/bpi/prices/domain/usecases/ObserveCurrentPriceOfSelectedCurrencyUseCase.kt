package dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.usecases

import dev.supasintatiyanupanwong.apps.android.bpi.currencies.domain.usecases.ObserveSelectedCurrencyCodeUseCase
import dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.PricesRepositoryContract
import dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.models.PriceRecord
import kotlinx.coroutines.flow.combine

class ObserveCurrentPriceOfSelectedCurrencyUseCase(
    private val pricesRepositoryContract: PricesRepositoryContract,
    private val observeSelectedCurrencyCodeUseCase: ObserveSelectedCurrencyCodeUseCase
) {

    operator fun invoke() = pricesRepositoryContract.observeCurrentPrice()
        .combine(observeSelectedCurrencyCodeUseCase()) { current, currencyCode ->
            current ?: return@combine null
            PriceRecord(
                timeMillis = current.timeMillis,
                price = current.prices
                    .find { it.currency.currencyCode == currencyCode }
                    ?: return@combine null
            )
        }

}
