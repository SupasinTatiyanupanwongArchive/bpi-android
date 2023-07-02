package dev.supasintatiyanupanwong.apps.android.bpi.prices.ui.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.models.PriceInfo
import dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.usecases.FetchCurrentPriceUseCase
import dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.usecases.FormatPriceUseCase
import dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.usecases.ObserveCurrentPriceOfSelectedCurrencyUseCase
import dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.usecases.ObservePriceRecordsOfSelectedCurrencyUseCase
import dev.supasintatiyanupanwong.apps.android.bpi.time.domain.usecases.FormatDateTimeUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PricesViewModel(
    private val fetchCurrentPriceUseCase: FetchCurrentPriceUseCase,
    observeCurrentPriceOfSelectedCurrencyUseCase: ObserveCurrentPriceOfSelectedCurrencyUseCase,
    observePriceRecordsOfSelectedCurrencyUseCase: ObservePriceRecordsOfSelectedCurrencyUseCase,
    private val formatPriceUseCase: FormatPriceUseCase,
    private val formatDateTimeUseCase: FormatDateTimeUseCase
) : ViewModel() {

    private val _loadingHint = MutableLiveData(false)
    val loadingHint = _loadingHint.asLiveData()

    val currentPriceOfSelectedCurrency = observeCurrentPriceOfSelectedCurrencyUseCase().asLiveData()

    val priceRecordsOfSelectedCurrency = observePriceRecordsOfSelectedCurrencyUseCase().asLiveData()

    init {
        viewModelScope.launch {
            while (true) {
                _loadingHint.value = true
                fetchCurrentPriceUseCase()
                _loadingHint.value = false
                delay(60_000L)
            }
        }
    }

    fun formatPriceAsString(price: PriceInfo?) = formatPriceUseCase(price)

    fun formatDateTimeAsString(timeMillis: Long?) = formatDateTimeUseCase(timeMillis ?: 0L)

}


fun <T> MutableLiveData<T>.asLiveData(): LiveData<T> = this
