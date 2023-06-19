package kt.parser

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.TypeAdapterFactory
import com.google.gson.internal.LazilyParsedNumber
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.io.IOException


class GsonTypeNumberAdapterFactory : TypeAdapterFactory {

    override fun <T> create(gson: Gson, type: TypeToken<T>): TypeAdapter<T> {
        val adapter = gson.getDelegateAdapter(this, type)
        return object : TypeAdapter<T>() {

            val delegate = gson.getAdapter(String::class.java)

            @Throws(IOException::class)
            override fun write(out: JsonWriter, value: T?) {
                if (value is LazilyParsedNumber) {
                    delegate.write(out, value.toInt().toString())
                } else {
                    adapter.write(out, value)
                }
            }

            @Throws(IOException::class)
            override fun read(reader: JsonReader): T? {
                return try {
                    adapter.read(reader)
                } catch (e: Throwable) {
                    consumeAll(reader)
                    e.printStackTrace()
                    null
                }
            }

            @Throws(IOException::class)
            private fun consumeAll(reader: JsonReader) {
                if (reader.hasNext()) {
                    val peek = reader.peek()
                    if (peek == JsonToken.STRING) {
                        reader.nextString()
                    } else if (peek == JsonToken.BEGIN_ARRAY) {
                        reader.beginArray()
                        consumeAll(reader)
                        reader.endArray()
                    } else if (peek == JsonToken.BEGIN_OBJECT) {
                        reader.beginObject()
                        consumeAll(reader)
                        reader.endObject()
                    } else if (peek == JsonToken.END_ARRAY) {
                        reader.endArray()
                    } else if (peek == JsonToken.END_OBJECT) {
                        reader.endObject()
                    } else if (peek == JsonToken.NUMBER) {
                        reader.nextString()
                    } else if (peek == JsonToken.BOOLEAN) {
                        reader.nextBoolean()
                    } else if (peek == JsonToken.NAME) {
                        reader.nextName()
                        consumeAll(reader)
                    } else if (peek == JsonToken.NULL) {
                        reader.nextNull()
                    }
                }
            }
        }
    }
}
