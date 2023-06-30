package dev.supasintatiyanupanwong.apps.android.bpi.prices.data.models.adapters

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import dev.supasintatiyanupanwong.apps.android.bpi.base.data.serializers.DateTimeDeserializer
import dev.supasintatiyanupanwong.apps.android.bpi.base.data.serializers.DateTimeSerializer
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
