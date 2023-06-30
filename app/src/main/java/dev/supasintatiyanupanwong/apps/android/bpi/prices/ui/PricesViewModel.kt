package dev.supasintatiyanupanwong.apps.android.bpi.prices.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.models.PriceInfo
import dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.usecases.FetchCurrentPriceUseCase
import dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.usecases.FormatPriceUseCase
import dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.usecases.ObserveCurrentPriceOfSelectedCurrencyUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PricesViewModel(
    private val fetchCurrentPriceUseCase: FetchCurrentPriceUseCase,
    private val observeCurrentPriceOfSelectedCurrencyUseCase: ObserveCurrentPriceOfSelectedCurrencyUseCase,
    private val formatPriceUseCase: FormatPriceUseCase
) : ViewModel() {

    init {
        viewModelScope.launch {
            while (true) {
                fetchCurrentPriceUseCase()
                delay(60_000L)
            }
        }
    }

    fun currentPriceOfSelectedCurrency() = observeCurrentPriceOfSelectedCurrencyUseCase()

    fun formatPriceAsString(price: PriceInfo?) = formatPriceUseCase(price)

}
