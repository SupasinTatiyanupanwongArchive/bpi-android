package dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.usecases

import dev.supasintatiyanupanwong.apps.android.bpi.currencies.domain.usecases.ObserveSelectedCurrencyCodeUseCase
import dev.supasintatiyanupanwong.apps.android.bpi.prices.data.PricesRepository
import dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.models.PriceRecord
import kotlinx.coroutines.flow.combine

class ObservePriceRecordsOfSelectedCurrencyUseCase(
    private val pricesRepository: PricesRepository,
    private val observeSelectedCurrencyCodeUseCase: ObserveSelectedCurrencyCodeUseCase
) {

    operator fun invoke() = pricesRepository.observePriceRecords()
        .combine(observeSelectedCurrencyCodeUseCase()) { records, currencyCode ->
            records?.mapNotNull { record ->
                PriceRecord(
                    timeMillis = record.timeMillis,
                    price = record.prices
                        .find { it.currency.currencyCode == currencyCode }
                        ?: return@mapNotNull null
                )
            }
        }

}
