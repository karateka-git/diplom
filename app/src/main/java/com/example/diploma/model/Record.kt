package com.example.diploma.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.diploma.repository.records.IRecordsRepository
import java.io.Serializable
import java.util.*

@Entity (tableName = "records_table")
data class Record (
    @PrimaryKey
    val uuid: UUID,
    var date: String,
    var title: String,
    var info: String,
    var type: String = IRecordsRepository.default
    ): Serializable