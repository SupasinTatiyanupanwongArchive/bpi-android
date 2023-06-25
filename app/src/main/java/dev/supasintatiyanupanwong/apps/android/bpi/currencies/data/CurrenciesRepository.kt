package dev.supasintatiyanupanwong.apps.android.bpi.currencies.data

import dev.supasintatiyanupanwong.apps.android.bpi.currencies.data.datasources.CurrenciesDataSource
import dev.supasintatiyanupanwong.apps.android.bpi.currencies.data.datasources.SelectedCurrencyCodeDataSource

class CurrenciesRepository(
    private val currenciesDataSource: CurrenciesDataSource,
    private val selectedCurrencyCodeDataSource: SelectedCurrencyCodeDataSource
) {

    fun getCurrencies() = currenciesDataSource.currencies

    fun observeSelectedCurrencyCode() = selectedCurrencyCodeDataSource.observeSelectedCurrencyCode()

    fun getSelectedCurrencyCode(): String? = selectedCurrencyCodeDataSource.selectedCurrencyCode

    fun setSelectedCurrencyCode(code: String?) {
        selectedCurrencyCodeDataSource.selectedCurrencyCode = code
    }

}
