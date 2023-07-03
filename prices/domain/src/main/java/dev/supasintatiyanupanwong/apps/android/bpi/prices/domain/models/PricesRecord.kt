package dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.models

data class PricesRecord(
    val timeMillis: Long,
    val disclaimer: String?,
    val prices: List<PriceInfo>
)


inline fun PricesRecord.find(predicate: (PriceInfo) -> Boolean): PriceRecord? {
    val price = prices.find(predicate) ?: return null
    return PriceRecord(
        timeMillis = timeMillis,
        disclaimer = disclaimer,
        price = price
    )
}
