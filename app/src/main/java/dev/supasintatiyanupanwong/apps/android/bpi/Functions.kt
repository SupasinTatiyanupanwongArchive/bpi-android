package dev.supasintatiyanupanwong.apps.android.bpi

inline fun <T : Any> tryOrNull(crossinline body: () -> T?): T? {
    return try {
        body()
    } catch (ex: Exception) {
        ex.printStackTrace()
        null
    }
}
