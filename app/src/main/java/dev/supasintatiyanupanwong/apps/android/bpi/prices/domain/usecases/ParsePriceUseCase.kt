package dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.usecases

import dev.supasintatiyanupanwong.apps.android.bpi.math.domain.usecases.ParseBigDecimalUseCase

class ParsePriceUseCase(private val source: ParseBigDecimalUseCase) {

    operator fun invoke(source: String) = source(source)

}
