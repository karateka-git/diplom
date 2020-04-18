package com.example.diploma.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.util.*

@Dao
interface RecordDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(record: Record)

    @Query("Select * from records_table")
    suspend fun getAll(): List<Record>

    @Query("Select * from records_table where uuid = :uuid")
    suspend fun getRecord(uuid: UUID): Record
}