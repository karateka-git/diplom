package com.example.diplom_DP.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.diplom_DP.db.converter.UUIDConverter
import com.example.diplom_DP.db.dao.RecordDao
import com.example.diplom_DP.db.entity.RecordEntity

@Database(entities = [RecordEntity::class],
    version = 9, exportSchema = false)
@TypeConverters(UUIDConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val recordDao : RecordDao
}