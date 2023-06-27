package dev.supasintatiyanupanwong.apps.android.bpi.math.di

import dev.supasintatiyanupanwong.apps.android.bpi.math.domain.utils.BigDecimalFormat
import org.koin.dsl.module

val mathModule = module {
    single { BigDecimalFormat }
}
