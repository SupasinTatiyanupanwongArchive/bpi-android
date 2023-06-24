package dev.supasintatiyanupanwong.apps.android.bpi.currentprice.data.storages

import android.app.Application
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dev.supasintatiyanupanwong.apps.android.bpi.base.data.BasePreferences
import dev.supasintatiyanupanwong.apps.android.bpi.currentprice.domain.models.PriceRecord

class PricesPreferencesStorage(
    application: Application,
    private val gson: Gson
) : BasePreferences(application, gson, "prices") {

    private val recordsType = object : TypeToken<List<PriceRecord>>() {}.type
    var records: List<PriceRecord>?
        get() = gson.fromJson(delegate.getString("records", null), recordsType)
        set(value) {
            delegate.edit { putString("records", gson.toJson(value)) }
        }

}
