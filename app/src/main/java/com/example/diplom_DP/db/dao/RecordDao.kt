package com.example.diplom_DP.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.diplom_DP.db.converter.DateConverter
import com.example.diplom_DP.db.entity.RecordEntity
import com.example.diplom_DP.model.Record
import java.util.*

@Dao
interface RecordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(record: RecordEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(records: List<RecordEntity>)

    @Query("Select * from records where type = :type order by isCompleted, timeFrom")
    fun getAllRecordsType(type: String): LiveData<List<RecordEntity>>

    @Query("Select * from records where " +
            "(type = '${Record.dailyRecord}' or " +
            "type = '${Record.universityRecord}') and " +
            "date = :date order by isCompleted, timeFrom")
    fun getAllRecords(date: Long): LiveData<List<RecordEntity>>

    @Query("Select * from records where " +
            "date >= :date order by isCompleted, timeFrom")
    fun getTodayAndFutureRecords(date: String): List<RecordEntity>

    @Query("Select * from records where uuid = :uuid")
    suspend fun getRecord(uuid: UUID): RecordEntity

    @Query("Delete from records where type = :type")
    suspend fun deleteRecordsType(type: String)

    @Query("Delete from records")
    suspend fun deleteAll()
}