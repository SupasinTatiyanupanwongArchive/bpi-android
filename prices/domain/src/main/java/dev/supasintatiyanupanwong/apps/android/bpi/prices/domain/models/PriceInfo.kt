package dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.models

import java.math.BigDecimal
import java.util.Currency

data class PriceInfo(
    val currency: Currency,
    // Using BigDecimal as we are dealing with price and currency, and
    // losing in precision could resulted in significant rounding error
    val value: BigDecimal
)
