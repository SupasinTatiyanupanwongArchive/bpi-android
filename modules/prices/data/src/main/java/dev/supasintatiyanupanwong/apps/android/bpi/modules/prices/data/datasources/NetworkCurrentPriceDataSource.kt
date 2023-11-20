package dev.supasintatiyanupanwong.apps.android.bpi.modules.prices.data.datasources

import dev.supasintatiyanupanwong.apps.android.bpi.modules.prices.data.apis.CurrentPriceApi

class NetworkCurrentPriceDataSource(private val api: CurrentPriceApi) {

    suspend fun getCurrentPrice() = api.getCurrentPrice()

}
