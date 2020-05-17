package com.example.diplom_DP.repository.records

import androidx.lifecycle.LiveData
import com.example.diplom_DP.db.entity.RecordEntity
import com.example.diplom_DP.model.Record
import java.util.*

interface IRecordsRepository<T:Record> {

    val valuesMap: LiveData<List<T>>

    fun newID(): UUID {
        return UUID.randomUUID()
    }

    fun getEmptyRecord(): T

    fun get(id: UUID): T?

    fun set(record: T)

    fun setAll(records: Map<UUID, T>)

    fun update(record: T)

    fun size(): Int {
        return valuesMap.value?.size?:0
    }

    fun clearRepository() {
    }

    fun updateUniversityRecords(records: Map<UUID, T>)

    fun getRecordsForBootService(date: Long): List<RecordEntity>
}