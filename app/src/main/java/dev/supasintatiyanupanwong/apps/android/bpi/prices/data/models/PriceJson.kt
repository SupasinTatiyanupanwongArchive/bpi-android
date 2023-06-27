package dev.supasintatiyanupanwong.apps.android.bpi.prices.data.models

import com.google.gson.annotations.SerializedName

data class PriceJson(
    @SerializedName("code")
    val code: String?,
    @SerializedName("rate")
    val rate: String?
)
