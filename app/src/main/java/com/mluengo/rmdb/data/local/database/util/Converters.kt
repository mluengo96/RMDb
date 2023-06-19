package com.mluengo.rmdb.data.local.database.util

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

@ProvidedTypeConverter
class LocationConverter(val moshi: Moshi) {
    @TypeConverter
    fun listToJson(value: List<String>): String? {
        val type = Types.newParameterizedType(List::class.java, String::class.java)
        val adapter = moshi.adapter<List<String>>(type)

        return adapter.toJson(value)
    }

    @TypeConverter
    fun jsonToList(json: String): List<String>? {
        val type = Types.newParameterizedType(List::class.java, String::class.java)
        val adapter = moshi.adapter<List<String>>(type)

        return adapter.fromJson(json)
    }
}