package dev.supasintatiyanupanwong.apps.android.bpi.currentprice.data.models

import com.google.gson.annotations.SerializedName

data class CurrentPriceJson(
    @SerializedName("time")
    val time: TimeJson?,
    @SerializedName("disclaimer")
    val disclaimer: String?,
    @SerializedName("chartName")
    val chartName: String?,
    @SerializedName("bpi")
    val bpi: BpisJson?
)
