package dev.supasintatiyanupanwong.apps.android.bpi.modules.prices.domain.usecases

import dev.supasintatiyanupanwong.apps.android.bpi.modules.math.domain.usecases.ParseBigDecimalUseCase

class ParsePriceUseCase(private val source: ParseBigDecimalUseCase) {

    operator fun invoke(source: String) = source(source)

}
