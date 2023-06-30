package dev.supasintatiyanupanwong.apps.android.bpi.prices.domain

import dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.models.PricesRecord
import kotlinx.coroutines.flow.Flow

interface PricesRepositoryContract {

    suspend fun fetchCurrentPrice()

    fun observeCurrentPrice(): Flow<PricesRecord?>

    fun observePriceRecords(): Flow<List<PricesRecord>?>

}
