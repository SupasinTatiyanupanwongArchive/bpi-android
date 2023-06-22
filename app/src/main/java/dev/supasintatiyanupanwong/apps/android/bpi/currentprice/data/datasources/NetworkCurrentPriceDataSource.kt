package dev.supasintatiyanupanwong.apps.android.bpi.currentprice.data.datasources

import dev.supasintatiyanupanwong.apps.android.bpi.currentprice.data.apis.CurrentPriceApi

class NetworkCurrentPriceDataSource(private val api: CurrentPriceApi) {

    suspend fun getCurrentPrice() = api.getCurrentPrice()

}
