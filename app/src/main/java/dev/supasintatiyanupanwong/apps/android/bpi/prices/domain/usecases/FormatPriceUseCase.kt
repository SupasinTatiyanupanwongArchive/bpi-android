package dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.usecases

import dev.supasintatiyanupanwong.apps.android.bpi.math.domain.usecases.FormatBigDecimalUseCase
import dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.models.PriceInfo
import java.math.BigDecimal

class FormatPriceUseCase(private val source: FormatBigDecimalUseCase) {

    operator fun invoke(price: BigDecimal) = source(price)

    operator fun invoke(price: PriceInfo?): String {
        price ?: return "N/A"
        return "${price.currency.symbol} ${source(price.value)}"
    }

}
