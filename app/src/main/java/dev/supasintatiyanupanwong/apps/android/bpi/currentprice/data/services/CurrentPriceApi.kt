package dev.supasintatiyanupanwong.apps.android.bpi.currentprice.data.services

import retrofit2.http.GET

interface CurrentPriceApi {

    @GET("currentprice.json")
    suspend fun getCurrentPrice(): String

}
