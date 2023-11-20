package dev.supasintatiyanupanwong.apps.android.bpi.modules.prices.domain.models

data class PriceRecord(
    val timeMillis: Long,
    val disclaimer: String?,
    val price: PriceInfo
)
