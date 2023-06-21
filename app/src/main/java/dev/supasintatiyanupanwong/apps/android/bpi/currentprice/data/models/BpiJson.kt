package dev.supasintatiyanupanwong.apps.android.bpi.currentprice.data.models

import com.google.gson.annotations.SerializedName

data class BpiJson(
    @SerializedName("code")
    val code: String?,
    @SerializedName("symbol")
    val symbol: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("rate")
    val rateString: String?,
    @SerializedName("rate_float")
    val rateFloat: Float?
)
