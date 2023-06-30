package dev.supasintatiyanupanwong.apps.android.bpi.math.domain.di

import dev.supasintatiyanupanwong.apps.android.bpi.math.domain.usecases.FormatBigDecimalUseCase
import dev.supasintatiyanupanwong.apps.android.bpi.math.domain.usecases.ParseBigDecimalUseCase
import dev.supasintatiyanupanwong.apps.android.bpi.math.domain.utils.BigDecimalFormat
import org.koin.dsl.module

val mathDomainModule = module {
    single { BigDecimalFormat }

    factory { FormatBigDecimalUseCase(get()) }
    factory { ParseBigDecimalUseCase(get()) }
}
