package dev.supasintatiyanupanwong.apps.android.bpi.prices.data.storages

import android.content.Context
import com.google.gson.Gson
import dev.supasintatiyanupanwong.apps.android.bpi.base.data.BasePreferences
import dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.models.PricesRecord

class PricesPreferencesStorage(
    context: Context,
    gson: Gson
) : BasePreferences(context, gson, "prices") {

    var records: List<PricesRecord>? by this["records"]

}
