package dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.models

data class PriceRecord(
    val timeMillis: Long,
    val prices: List<PriceInfo>
)
