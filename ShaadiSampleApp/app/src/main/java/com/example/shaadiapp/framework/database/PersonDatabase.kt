package com.example.shaadiapp.framework.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.shaadiapp.domain.entities.PersonRequestEntity

@Database(entities = [PersonRequestEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class PersonDatabase : RoomDatabase() {
    abstract fun personRequestDao(): PersonRequestDao
}