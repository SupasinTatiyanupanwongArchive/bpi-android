package dev.supasintatiyanupanwong.apps.android.bpi.modules.math.domain.utils

import java.math.BigDecimal
import java.text.DecimalFormat

object BigDecimalFormat : DecimalFormat() {

    init {
        super.setParseBigDecimal(true)
    }

    override fun setParseBigDecimal(newValue: Boolean) {
        if (!newValue) {
            throw UnsupportedOperationException("BigDecimalFormat must parse as BigDecimal")
        }
    }

    override fun parse(source: String): BigDecimal {
        return super.parse(source) as BigDecimal
    }

}
