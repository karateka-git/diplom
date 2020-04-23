package com.example.diploma.repository.records

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.diploma.model.Record
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
}