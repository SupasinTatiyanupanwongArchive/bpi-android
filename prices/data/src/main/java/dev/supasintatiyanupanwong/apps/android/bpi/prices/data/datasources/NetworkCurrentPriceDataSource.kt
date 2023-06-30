package dev.supasintatiyanupanwong.apps.android.bpi.prices.data.datasources

import dev.supasintatiyanupanwong.apps.android.bpi.prices.data.apis.CurrentPriceApi

class NetworkCurrentPriceDataSource(private val api: CurrentPriceApi) {

    suspend fun getCurrentPrice() = api.getCurrentPrice()

}
