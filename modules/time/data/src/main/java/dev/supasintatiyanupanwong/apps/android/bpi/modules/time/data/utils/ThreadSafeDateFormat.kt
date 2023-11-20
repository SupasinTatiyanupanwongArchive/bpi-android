package dev.supasintatiyanupanwong.apps.android.bpi.modules.time.data.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class ThreadSafeDateFormat(
    private val pattern: String,
    private val zoneId: String = "UTC"
) {

    private val format = object : ThreadLocal<SimpleDateFormat>() {
        override fun initialValue() =
            SimpleDateFormat(pattern, Locale.US).apply { timeZone = TimeZone.getTimeZone(zoneId) }
    }

    fun parse(value: String?): Date? =
        if (value == null) null else format.get()!!.parse(value)

    fun format(value: Any?): String? =
        if (value == null) null else format.get()!!.format(value)

}
