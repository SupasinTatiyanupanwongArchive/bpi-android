package dev.supasintatiyanupanwong.apps.android.bpi.modules.prices.data.models

import com.google.gson.annotations.SerializedName

data class BpiJson(
    @SerializedName("USD")
    val usd: PriceJson?,
    @SerializedName("GBP")
    val gbp: PriceJson?,
    @SerializedName("EUR")
    val eur: PriceJson?
)
