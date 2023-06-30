package dev.supasintatiyanupanwong.apps.android.bpi.currencies.data.datasources

import dev.supasintatiyanupanwong.apps.android.bpi.currencies.data.preferences.CurrenciesPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlin.properties.Delegates

class SelectedCurrencyCodeDataSource(private val currenciesPreferences: CurrenciesPreferences) {

    private val flow = MutableStateFlow(value = currenciesPreferences.selectedCurrencyCode)

    var selectedCurrencyCode by Delegates.observable(
        initialValue = currenciesPreferences.selectedCurrencyCode
    ) { _, _, newValue ->
        currenciesPreferences.selectedCurrencyCode = newValue.also { flow.value = it }
    }

    fun observeSelectedCurrencyCode() = flow.asSharedFlow()

}
