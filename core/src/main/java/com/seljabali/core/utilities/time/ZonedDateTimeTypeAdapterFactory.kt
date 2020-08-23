package com.seljabali.core.utilities.time

import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import com.google.gson.TypeAdapter
import com.google.gson.reflect.TypeToken
import com.google.gson.Gson
import com.google.gson.TypeAdapterFactory
import com.google.gson.stream.JsonToken
import java.time.ZonedDateTime

class ZonedDateTimeTypeAdapterFactory : TypeAdapter<ZonedDateTime>() {

    companion object {
        val FACTORY: TypeAdapterFactory = object : TypeAdapterFactory {
            override fun <T> create(gson: Gson, type: TypeToken<T>): TypeAdapter<T>? {
                return if (type.rawType == ZonedDateTime::class.java) ZonedDateTimeTypeAdapterFactory() as TypeAdapter<T> else null
            }
        }
    }

    override fun write(out: JsonWriter, value: ZonedDateTime?) {
        if (value == null) {
            out.nullValue()
            return
        }
        out.value(value.print(Formats.YearMonthDayTime.YYYY_MM_DD_TIME_Z))
    }

    override fun read(`in`: JsonReader?): ZonedDateTime? {
        val reader = `in` ?: return null
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull()
            return null
        }
        val jsonString = reader.nextString()
        if (jsonString.isMsftDate()) return jsonString.parseMsftDate()
        return jsonString.parseZonedDate()
    }
}