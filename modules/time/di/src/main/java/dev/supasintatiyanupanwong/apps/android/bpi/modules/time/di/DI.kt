package dev.supasintatiyanupanwong.apps.android.bpi.modules.time.di

import dev.supasintatiyanupanwong.apps.android.bpi.modules.time.domain.usecases.FormatDateTimeUseCase
import org.koin.dsl.module

val timeModule = module {
    factory { FormatDateTimeUseCase() }
}
