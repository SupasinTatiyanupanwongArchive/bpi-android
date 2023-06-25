package dev.supasintatiyanupanwong.apps.android.bpi.currencies.data.datasources

import dev.supasintatiyanupanwong.apps.android.bpi.prices.data.datasources.LocalPricesDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.Currency

class CurrenciesDataSource(localPricesDataSource: LocalPricesDataSource) {

    var currencies: List<Currency>? = null
        private set

    init {
        localPricesDataSource.observePriceRecords()
            .onEach { records ->
                currencies = records
                    ?.flatMap { it.prices }
                    ?.map { it.currency }
                    ?.distinct()
            }
            .launchIn(CoroutineScope(Dispatchers.IO))
    }

}
