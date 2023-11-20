package dev.supasintatiyanupanwong.apps.android.bpi.modules.currencies.data.preferences

import android.content.Context
import com.google.gson.Gson
import dev.supasintatiyanupanwong.apps.android.bpi.base.data.BasePreferences

class CurrenciesPreferences(
    context: Context,
    gson: Gson
) : BasePreferences(context, gson, "currencies") {

    var selectedCurrencyCode: String? by this["selected_currency_code"]

}
