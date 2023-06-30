package dev.supasintatiyanupanwong.apps.android.bpi.platform.jvm

// Mimicking DSL
inline operator fun <T> T.invoke(crossinline action: T.() -> Unit): T {
    action()
    return this
}
