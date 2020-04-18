package com.example.diploma.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.diploma.db.converter.UUIDConverter
import com.example.diploma.model.Record
import com.example.diploma.model.RecordDao

@Database(entities = [Record::class], version = 1, exportSchema = false)
@TypeConverters(UUIDConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val recordDao : RecordDao
}