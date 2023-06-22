package dev.supasintatiyanupanwong.apps.android.bpi.currentprice.data.models

import com.google.gson.annotations.SerializedName

data class CurrentPriceJson(
    @SerializedName("time")
    val time: TimeJson?,
    @SerializedName("bpi")
    val bpi: BpiJson?
)
