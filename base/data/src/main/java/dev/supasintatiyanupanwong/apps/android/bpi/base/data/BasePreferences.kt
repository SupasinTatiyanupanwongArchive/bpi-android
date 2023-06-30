package dev.supasintatiyanupanwong.apps.android.bpi.base.data

import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import kotlin.reflect.KProperty

abstract class BasePreferences(context: Context, gson: Gson, name: String) {

    private val prefs = SharedPreferencesWrapper(context, gson, name)

    open val isFallbackToDestructiveMigration = false

    /**
     * Returns a property which can be used to access the associated value from the preferences.
     */
    protected inline operator fun <reified T : Any> get(key: String) =
        Property<T>(key, object : TypeToken<T>() {}.type)

    /**
     * Removes all values from the preferences.
     */
    fun clear() = prefs.clear()


    protected inner class Property<T : Any>(
        private val key: String,
        private val type: Type
    ) {

        private var value: T?
            get() = prefs.get(key, type)
            set(value) {
                if (value == null) prefs.remove(key) else prefs.set(key, value)
            }


        // Allows using this property in "by" statement; e.g. var user: User? by this["user"]
        operator fun getValue(thisRef: Any?, property: KProperty<*>): T? {
            return value
        }

        operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
            this.value = value
        }

    }


    private inner class SharedPreferencesWrapper(
        context: Context,
        private val gson: Gson,
        name: String
    ) {

        private val prefs =
            context.getSharedPreferences("${context.packageName}.$name", Context.MODE_PRIVATE)

        /**
         * Gets a value associated with [key] from the preferences according to its [type].
         */
        @Suppress("UNCHECKED_CAST")
        fun <T : Any> get(key: String, type: Type): T? {
            return if (!prefs.contains(key)) {
                null
            } else {
                try {
                    when (type) {
                        String::class.java -> prefs.getString(key, null) as T?
                        Long::class.java -> prefs.getLong(key, -1L) as T?
                        Boolean::class.java -> prefs.getBoolean(key, false) as T?
                        else -> gson.fromJson(prefs.getString(key, null), type)
                    }
                } catch (ex: Exception) {
                    if (isFallbackToDestructiveMigration) {
                        remove(key)
                        null
                    } else {
                        throw ex
                    }
                }
            }
        }

        /**
         * Saves a [value] associated with [key] into the preferences according to its type.
         */
        fun <T : Any> set(key: String, value: T) {
            prefs.edit {
                when (value) {
                    is String -> putString(key, value)
                    is Long -> putLong(key, value)
                    is Boolean -> putBoolean(key, value)
                    else -> putString(key, gson.toJson(value))
                }
            }
        }

        /**
         * Removes a value associated with [key] from the preferences.
         */
        fun remove(key: String) = prefs.edit().remove(key).apply()

        /**
         * Removes all values from the preferences.
         */
        fun clear() = prefs.edit().clear().apply()

    }

}
