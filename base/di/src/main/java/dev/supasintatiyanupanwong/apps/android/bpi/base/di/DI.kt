package dev.supasintatiyanupanwong.apps.android.bpi.base.di

import com.google.gson.Gson
import dev.supasintatiyanupanwong.apps.android.bpi.base.data.services.ClientProvider
import dev.supasintatiyanupanwong.apps.android.bpi.base.data.services.CoindeskService
import org.koin.dsl.module

val baseModule = module {
    single { Gson() }
    single { ClientProvider() }
    single { CoindeskService(get()) }
}
