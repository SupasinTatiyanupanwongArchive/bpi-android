package dev.supasintatiyanupanwong.apps.android.bpi.base.data

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

abstract class BasePreferences(context: Context, gson: Gson, name: String) {

    protected val delegate = context.getSharedPreferences("${context.packageName}.$name")

}


@Suppress("NOTHING_TO_INLINE")
inline fun Context.getSharedPreferences(name: String): SharedPreferences {
    return getSharedPreferences(name, Context.MODE_PRIVATE)
}
