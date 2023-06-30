package dev.supasintatiyanupanwong.apps.android.bpi.prices.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.models.PriceInfo
import dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.usecases.FetchCurrentPriceUseCase
import dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.usecases.FormatPriceUseCase
import dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.usecases.ObserveCurrentPriceOfSelectedCurrencyUseCase
import dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.usecases.ObservePriceRecordsOfSelectedCurrencyUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PricesViewModel(
    private val fetchCurrentPriceUseCase: FetchCurrentPriceUseCase,
    observeCurrentPriceOfSelectedCurrencyUseCase: ObserveCurrentPriceOfSelectedCurrencyUseCase,
    observePriceRecordsOfSelectedCurrencyUseCase: ObservePriceRecordsOfSelectedCurrencyUseCase,
    private val formatPriceUseCase: FormatPriceUseCase
) : ViewModel() {

    val currentPriceOfSelectedCurrency = observeCurrentPriceOfSelectedCurrencyUseCase().asLiveData()

    val priceRecordsOfSelectedCurrency = observePriceRecordsOfSelectedCurrencyUseCase().asLiveData()

    init {
        viewModelScope.launch {
            while (true) {
                fetchCurrentPriceUseCase()
                delay(60_000L)
            }
        }
    }

    fun formatPriceAsString(price: PriceInfo?) = formatPriceUseCase(price)

}
