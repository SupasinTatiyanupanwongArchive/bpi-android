package dev.supasintatiyanupanwong.apps.android.bpi.platform.jvm

inline fun <T : Any> tryOrNull(crossinline body: () -> T?): T? {
    return try {
        body()
    } catch (ex: Exception) {
        ex.printStackTrace()
        null
    }
}

suspend inline fun <T : Any> suspendTryOrNull(crossinline body: suspend () -> T?): T? {
    return try {
        body()
    } catch (ex: Exception) {
        ex.printStackTrace()
        null
    }
}
