package com.example.diploma.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.diploma.db.converter.UUIDConverter
import com.example.diploma.db.dao.RecordDao
import com.example.diploma.db.entity.RecordEntity

@Database(entities = [RecordEntity::class],
    version = 8, exportSchema = false)
@TypeConverters(UUIDConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val recordDao : RecordDao
}