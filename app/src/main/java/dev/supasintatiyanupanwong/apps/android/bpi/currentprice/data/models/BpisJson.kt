package dev.supasintatiyanupanwong.apps.android.bpi.currentprice.data.models

import com.google.gson.annotations.SerializedName

data class BpisJson(
    @SerializedName("USD")
    val usd: BpiJson?,
    @SerializedName("GBP")
    val gbp: BpiJson?,
    @SerializedName("EUR")
    val eur: BpiJson?
)
