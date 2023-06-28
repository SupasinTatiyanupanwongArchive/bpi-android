package dev.supasintatiyanupanwong.apps.android.bpi.math.domain.usecases

import dev.supasintatiyanupanwong.apps.android.bpi.math.domain.utils.BigDecimalFormat

class FormatBigDecimalUseCase(private val format: BigDecimalFormat) {

    operator fun invoke(any: Any): String = format.format(any)

}
