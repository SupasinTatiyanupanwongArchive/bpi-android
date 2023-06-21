package dev.supasintatiyanupanwong.apps.android.bpi.currentprice.data.models

import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import dev.supasintatiyanupanwong.apps.android.bpi.currentprice.data.models.adapters.CurrentPriceDateTimeAdapter

data class TimeJson(
    @JsonAdapter(CurrentPriceDateTimeAdapter::class)
    @SerializedName("updatedISO")
    val updatedAt: Long?
)
