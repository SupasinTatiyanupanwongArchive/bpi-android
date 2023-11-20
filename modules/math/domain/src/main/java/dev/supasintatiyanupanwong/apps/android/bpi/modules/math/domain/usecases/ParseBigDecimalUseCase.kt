package dev.supasintatiyanupanwong.apps.android.bpi.modules.math.domain.usecases

import dev.supasintatiyanupanwong.apps.android.bpi.modules.math.domain.utils.BigDecimalFormat

class ParseBigDecimalUseCase(private val format: BigDecimalFormat) {

    operator fun invoke(source: String) = format.parse(source)

}
