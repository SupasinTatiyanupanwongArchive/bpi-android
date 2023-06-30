package dev.supasintatiyanupanwong.apps.android.bpi.currencies.data

import dev.supasintatiyanupanwong.apps.android.bpi.currencies.data.datasources.CurrenciesDataSource
import dev.supasintatiyanupanwong.apps.android.bpi.currencies.data.datasources.SelectedCurrencyCodeDataSource
import dev.supasintatiyanupanwong.apps.android.bpi.currencies.domain.CurrenciesRepositoryContract

class CurrenciesRepository(
    private val currenciesDataSource: CurrenciesDataSource,
    private val selectedCurrencyCodeDataSource: SelectedCurrencyCodeDataSource
) : CurrenciesRepositoryContract {

    override fun getCurrencies() = currenciesDataSource.currencies

    override fun observeSelectedCurrencyCode() =
        selectedCurrencyCodeDataSource.observeSelectedCurrencyCode()

    override fun getSelectedCurrencyCode(): String? =
        selectedCurrencyCodeDataSource.selectedCurrencyCode

    override fun setSelectedCurrencyCode(code: String?) {
        selectedCurrencyCodeDataSource.selectedCurrencyCode = code
    }

}
