package dev.supasintatiyanupanwong.apps.android.bpi.prices.ui.di

import dev.supasintatiyanupanwong.apps.android.bpi.prices.ui.screens.PricesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val pricesUiModule = module {
    viewModel { PricesViewModel(get(), get(), get(), get(), get()) }
}
