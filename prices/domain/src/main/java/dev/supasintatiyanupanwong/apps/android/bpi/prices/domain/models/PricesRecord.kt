package dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.models

data class PricesRecord(
    val timeMillis: Long,
    val prices: List<PriceInfo>
)
