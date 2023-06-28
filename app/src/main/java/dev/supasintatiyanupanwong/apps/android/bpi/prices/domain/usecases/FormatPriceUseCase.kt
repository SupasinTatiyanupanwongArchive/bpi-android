package dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.usecases

import dev.supasintatiyanupanwong.apps.android.bpi.math.domain.usecases.FormatBigDecimalUseCase

class FormatPriceUseCase(private val source: FormatBigDecimalUseCase) {

    operator fun invoke(any: Any) = source(any)

}
