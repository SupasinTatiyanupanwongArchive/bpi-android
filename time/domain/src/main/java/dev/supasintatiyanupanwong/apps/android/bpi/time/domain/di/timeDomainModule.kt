package dev.supasintatiyanupanwong.apps.android.bpi.time.domain.di

import dev.supasintatiyanupanwong.apps.android.bpi.time.domain.usecases.FormatDateTimeUseCase
import org.koin.dsl.module

val timeDomainModule = module {
    factory { FormatDateTimeUseCase() }
}
