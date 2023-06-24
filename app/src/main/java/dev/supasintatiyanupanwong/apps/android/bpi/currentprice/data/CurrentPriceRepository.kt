package dev.supasintatiyanupanwong.apps.android.bpi.currentprice.data

import dev.supasintatiyanupanwong.apps.android.bpi.currentprice.data.datasources.LocalCurrentPriceDataSource
import dev.supasintatiyanupanwong.apps.android.bpi.currentprice.data.datasources.NetworkCurrentPriceDataSource
import dev.supasintatiyanupanwong.apps.android.bpi.currentprice.data.mappers.CurrentPriceMapper
import dev.supasintatiyanupanwong.apps.android.bpi.currentprice.domain.models.PriceRecord
import dev.supasintatiyanupanwong.apps.android.bpi.suspendTryOrNull
import kotlinx.coroutines.flow.Flow

class CurrentPriceRepository(
    private val localCurrentPriceDataSource: LocalCurrentPriceDataSource,
    private val networkCurrentPriceDataSource: NetworkCurrentPriceDataSource,
    private val currentPriceMapper: CurrentPriceMapper
) {

    suspend fun fetchCurrentPrice() {
        val res = suspendTryOrNull { networkCurrentPriceDataSource.getCurrentPrice() } ?: return
        localCurrentPriceDataSource.save(currentPriceMapper.transform(res))
    }

    fun observeCurrentPrice(): Flow<PriceRecord?> {
        return localCurrentPriceDataSource.observeCurrentPrice()
    }

}
