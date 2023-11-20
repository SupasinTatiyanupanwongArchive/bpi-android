package dev.supasintatiyanupanwong.apps.android.bpi.modules.math.di

import dev.supasintatiyanupanwong.apps.android.bpi.modules.math.domain.usecases.FormatBigDecimalUseCase
import dev.supasintatiyanupanwong.apps.android.bpi.modules.math.domain.usecases.ParseBigDecimalUseCase
import dev.supasintatiyanupanwong.apps.android.bpi.modules.math.domain.utils.BigDecimalFormat
import org.koin.dsl.module

val mathModule = module {
    single { BigDecimalFormat }

    factory { FormatBigDecimalUseCase(get()) }
    factory { ParseBigDecimalUseCase(get()) }
}
