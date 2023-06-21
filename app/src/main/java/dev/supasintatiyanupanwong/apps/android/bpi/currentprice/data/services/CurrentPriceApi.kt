package dev.supasintatiyanupanwong.apps.android.bpi.currentprice.data.services

import dev.supasintatiyanupanwong.apps.android.bpi.currentprice.data.models.CurrentPriceJson
import retrofit2.http.GET

interface CurrentPriceApi {

    @GET("currentprice.json")
    suspend fun getCurrentPrice(): CurrentPriceJson?

}
