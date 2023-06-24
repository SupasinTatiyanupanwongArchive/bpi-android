package dev.supasintatiyanupanwong.apps.android.bpi.currentprice.domain.models

data class PriceRecord(
    val timeMillis: Long,
    val prices: List<PriceInfo>
)
