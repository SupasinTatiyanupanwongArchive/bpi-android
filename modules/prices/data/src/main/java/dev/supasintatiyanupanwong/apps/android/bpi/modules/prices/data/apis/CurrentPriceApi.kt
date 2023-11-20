package dev.supasintatiyanupanwong.apps.android.bpi.modules.prices.data.apis

import dev.supasintatiyanupanwong.apps.android.bpi.modules.prices.data.models.CurrentPriceJson
import retrofit2.http.GET

interface CurrentPriceApi {

    @GET("currentprice.json")
    suspend fun getCurrentPrice(): CurrentPriceJson?

}
