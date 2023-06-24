package dev.supasintatiyanupanwong.apps.android.bpi.prices.data

import dev.supasintatiyanupanwong.apps.android.bpi.prices.data.datasources.LocalPricesDataSource
import dev.supasintatiyanupanwong.apps.android.bpi.prices.data.datasources.NetworkCurrentPriceDataSource
import dev.supasintatiyanupanwong.apps.android.bpi.prices.data.mappers.CurrentPriceMapper
import dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.models.PriceRecord
import dev.supasintatiyanupanwong.apps.android.bpi.suspendTryOrNull
import kotlinx.coroutines.flow.Flow

class PricesRepository(
    private val localPricesDataSource: LocalPricesDataSource,
    private val networkCurrentPriceDataSource: NetworkCurrentPriceDataSource,
    private val currentPriceMapper: CurrentPriceMapper
) {

    suspend fun fetchCurrentPrice() {
        val res = suspendTryOrNull { networkCurrentPriceDataSource.getCurrentPrice() } ?: return
        localPricesDataSource.save(currentPriceMapper.transform(res))
    }

    fun observeCurrentPrice(): Flow<PriceRecord?> {
        return localPricesDataSource.observeCurrentPrice()
    }

}
