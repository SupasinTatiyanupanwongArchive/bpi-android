package dev.supasintatiyanupanwong.apps.android.bpi.prices.data

import dev.supasintatiyanupanwong.apps.android.bpi.platform.jvm.suspendTryOrNull
import dev.supasintatiyanupanwong.apps.android.bpi.prices.data.datasources.LocalPricesDataSource
import dev.supasintatiyanupanwong.apps.android.bpi.prices.data.datasources.NetworkCurrentPriceDataSource
import dev.supasintatiyanupanwong.apps.android.bpi.prices.data.mappers.CurrentPriceMapper
import dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.PricesRepositoryContract
import dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.models.PricesRecord
import kotlinx.coroutines.flow.Flow

class PricesRepository(
    private val localPricesDataSource: LocalPricesDataSource,
    private val networkCurrentPriceDataSource: NetworkCurrentPriceDataSource,
    private val currentPriceMapper: CurrentPriceMapper
) : PricesRepositoryContract {

    override suspend fun fetchCurrentPrice() {
        val res = suspendTryOrNull { networkCurrentPriceDataSource.getCurrentPrice() } ?: return
        localPricesDataSource.save(currentPriceMapper.transform(res))
    }

    override fun observeCurrentPrice(): Flow<PricesRecord?> {
        return localPricesDataSource.observeCurrentPrice()
    }

    override fun observePriceRecords(): Flow<List<PricesRecord>?> {
        return localPricesDataSource.observePriceRecords()
    }

}
