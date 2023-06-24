package dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.models

import java.util.Currency

data class PriceInfo(
    val currency: Currency,
    val value: Double
)
