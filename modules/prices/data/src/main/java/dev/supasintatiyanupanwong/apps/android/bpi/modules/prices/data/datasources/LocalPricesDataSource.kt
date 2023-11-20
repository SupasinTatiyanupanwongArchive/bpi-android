package dev.supasintatiyanupanwong.apps.android.bpi.modules.prices.data.datasources

import dev.supasintatiyanupanwong.apps.android.bpi.modules.prices.data.storages.PricesPreferencesStorage
import dev.supasintatiyanupanwong.apps.android.bpi.modules.prices.domain.models.PricesRecord
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow

class LocalPricesDataSource(private val pricesPreferencesStorage: PricesPreferencesStorage) {

    private val recordsFlow = MutableStateFlow(pricesPreferencesStorage.records)

    // Dedicated flow for current price to optimise list reading and writing
    private val currentFlow = MutableStateFlow(recordsFlow.value?.maxByOrNull { it.timeMillis })

    fun observePriceRecords() = recordsFlow.asSharedFlow()

    fun observeCurrentPrice() = currentFlow.asSharedFlow()

    fun save(data: PricesRecord?) {
        if (data == null) return

        if (data.timeMillis == currentFlow.value?.timeMillis) return // Duplicate record

        if (data.timeMillis > (currentFlow.value?.timeMillis ?: 0L)) {
            currentFlow.value = data
        }

        val existing = pricesPreferencesStorage.records.orEmpty()
        pricesPreferencesStorage.records = existing.toMutableList()
            .apply { this += data }
            .also { recordsFlow.value = it }
    }

}
