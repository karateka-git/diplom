package com.example.diploma.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.diploma.db.entity.RecordEntity
import com.example.diploma.model.Record
import java.util.*

@Dao
interface RecordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(record: RecordEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(records: List<RecordEntity>)

    @Query("Select * from records where type = :type")
    fun getAllRecordsType(type: String): LiveData<List<RecordEntity>>

    @Query("Select * from records where " +
            "(type = '${Record.dailyRecord}' or " +
            "type = '${Record.universityRecord}') and " +
            "date = :date")
    fun getAllRecords(date: String): LiveData<List<RecordEntity>>

    @Query("Select * from records where uuid = :uuid")
    suspend fun getRecord(uuid: UUID): RecordEntity

    @Query("Delete from records where type = :type")
    suspend fun deleteRecordsType(type: String)

    @Query("Delete from records")
    suspend fun deleteAll()
}