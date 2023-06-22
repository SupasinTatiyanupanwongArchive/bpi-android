package dev.supasintatiyanupanwong.apps.android.bpi.currentprice.domain.models

import java.math.BigDecimal
import java.util.Currency

data class PriceInfo(
    val currency: Currency,
    val value: Double
)
