package dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.usecases

import dev.supasintatiyanupanwong.apps.android.bpi.currencies.domain.usecases.ObserveSelectedCurrencyCodeUseCase
import dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.PricesRepositoryContract
import dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.models.find
import kotlinx.coroutines.flow.combine

class ObservePriceRecordsOfSelectedCurrencyUseCase(
    private val pricesRepositoryContract: PricesRepositoryContract,
    private val observeSelectedCurrencyCodeUseCase: ObserveSelectedCurrencyCodeUseCase
) {

    operator fun invoke() = pricesRepositoryContract.observePriceRecords()
        .combine(observeSelectedCurrencyCodeUseCase()) { records, currencyCode ->
            records?.mapNotNull { record ->
                record.find { it.currency.currencyCode == currencyCode } ?: return@mapNotNull null
            }
        }

}
