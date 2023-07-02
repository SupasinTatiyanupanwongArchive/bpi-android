package dev.supasintatiyanupanwong.apps.android.bpi.time.data.serializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import dev.supasintatiyanupanwong.apps.android.bpi.platform.jvm.tryOrNull
import dev.supasintatiyanupanwong.apps.android.bpi.time.data.utils.ThreadSafeDateFormat
import java.lang.reflect.Type

open class DateTimeDeserializer(
    pattern: String,
    timeZone: String = "UTC"
) : JsonDeserializer<Long?> {

    private val format = ThreadSafeDateFormat(pattern, timeZone)

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Long? {
        return tryOrNull { format.parse(json?.asString)?.time }
    }

}
