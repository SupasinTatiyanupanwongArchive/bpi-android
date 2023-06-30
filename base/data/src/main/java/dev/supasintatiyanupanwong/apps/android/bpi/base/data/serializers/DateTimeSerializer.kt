package dev.supasintatiyanupanwong.apps.android.bpi.base.data.serializers

import com.google.gson.JsonElement
import com.google.gson.JsonNull
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import dev.supasintatiyanupanwong.apps.android.bpi.base.data.utils.ThreadSafeDateFormat
import java.lang.reflect.Type

open class DateTimeSerializer(
    pattern: String,
    timeZone: String = "UTC"
) : JsonSerializer<Long?> {

    private val formatter = ThreadSafeDateFormat(pattern, timeZone)

    override fun serialize(
        src: Long?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return when (val value = formatter.format(src)) {
            null -> JsonNull.INSTANCE
            else -> JsonPrimitive(value)
        }
    }

}
