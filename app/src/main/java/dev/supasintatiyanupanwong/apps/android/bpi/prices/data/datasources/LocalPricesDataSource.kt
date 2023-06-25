package dev.supasintatiyanupanwong.apps.android.bpi.prices.data.datasources

import dev.supasintatiyanupanwong.apps.android.bpi.prices.data.storages.PricesPreferencesStorage
import dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.models.PriceRecord
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map

class LocalPricesDataSource(private val pricesPreferencesStorage: PricesPreferencesStorage) {

    private val recordsFlow = MutableStateFlow(value = pricesPreferencesStorage.records)

    // Optimise list reading and writing
    private var latestTimeMillis = recordsFlow.value?.maxByOrNull { it.timeMillis } ?: -1L

    fun observePriceRecords() = recordsFlow.asSharedFlow()

    fun observeCurrentPrice() = observePriceRecords()
        .map { records -> records?.find { it.timeMillis == latestTimeMillis } }

    fun save(data: PriceRecord?) {
        if (data == null) return

        if (data.timeMillis == latestTimeMillis) return // Duplicate record
        latestTimeMillis = data.timeMillis

        val existing = pricesPreferencesStorage.records.orEmpty()
        pricesPreferencesStorage.records = existing.toMutableList()
            .apply { this += data }
            .also { recordsFlow.value = it }
    }

}
