package dev.supasintatiyanupanwong.apps.android.bpi.currencies.domain

import kotlinx.coroutines.flow.Flow
import java.util.Currency

interface CurrenciesRepositoryContract {

    fun getCurrencies(): List<Currency>?

    fun observeSelectedCurrencyCode(): Flow<String?>

    fun getSelectedCurrencyCode(): String?

    fun setSelectedCurrencyCode(code: String?)

}
