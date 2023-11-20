package dev.supasintatiyanupanwong.apps.android.bpi.modules.prices.data.models.adapters

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import dev.supasintatiyanupanwong.apps.android.bpi.modules.time.data.serialization.DateTimeDeserializer
import dev.supasintatiyanupanwong.apps.android.bpi.modules.time.data.serialization.DateTimeSerializer
import java.lang.reflect.Type

class CurrentPriceDateTimeAdapter : JsonDeserializer<Long?>, JsonSerializer<Long?> {

    private val serializer = DateTimeSerializer("yyyy-MM-dd'T'HH:mm:ssXXX")
    private val deserializer = DateTimeDeserializer("yyyy-MM-dd'T'HH:mm:ssXXX")

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Long? {
        return deserializer.deserialize(json, typeOfT, context)
    }

    override fun serialize(
        src: Long?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return serializer.serialize(src, typeOfSrc, context)
    }

}
