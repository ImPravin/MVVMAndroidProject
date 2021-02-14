package com.example.shaadiapp.framework.database

import androidx.room.TypeConverter
import com.example.shaadiapp.domain.entities.Dob
import com.example.shaadiapp.domain.entities.Location
import com.example.shaadiapp.domain.entities.Name
import com.example.shaadiapp.domain.entities.Picture
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class Converters {
    companion object {
        @TypeConverter
        @JvmStatic
        fun fromPicture(value: Picture): String {
            val gson = Gson()
            val type: Type = genericType<Picture>()
            return gson.toJson(value, type)
        }

        @TypeConverter
        @JvmStatic
        fun toPicture(value: String): Picture {
            val gson = Gson()
            val type: Type = genericType<Picture>()
            return gson.fromJson(value, type);
        }

        @TypeConverter
        @JvmStatic
        fun fromDob(value: Dob): String {
            val gson = Gson()
            val type: Type = genericType<Dob>()
            return gson.toJson(value, type)
        }

        @TypeConverter
        @JvmStatic
        fun toDob(value: String): Dob {
            val gson = Gson()
            val type: Type = genericType<Dob>()
            return gson.fromJson(value, type);
        }

        @TypeConverter
        @JvmStatic
        fun fromLocation(value: Location): String {
            val gson = Gson()
            val type: Type = genericType<Location>()
            return gson.toJson(value, type)
        }

        @TypeConverter
        @JvmStatic
        fun toLocation(value: String): Location {
            val gson = Gson()
            val type: Type = genericType<Location>()
            return gson.fromJson(value, type);
        }

        @TypeConverter
        @JvmStatic
        fun fromName(value: Name): String {
            val gson = Gson()
            val type: Type = genericType<Name>()
            return gson.toJson(value, type)
        }

        @TypeConverter
        @JvmStatic
        fun toName(value: String): Name {
            val gson = Gson()
            val type: Type = genericType<Name>()
            return gson.fromJson(value, type);
        }

        private inline fun <reified T> genericType() = object : TypeToken<T>() {}.type
    }
}