package com.example.diploma.repository.records

import com.example.diploma.model.Record
import java.util.*

interface IRecordsRepository {
    companion object {
        const val default = "default"
        const val dailyRecordsRepository = "daily"
        const val universityRecordsRepository = "university"
        const val holidayRecordsRepository = "holiday"
    }

    val valuesMap: MutableMap<UUID, Record>

    val type: String
        get() = default

    fun getEmptyRecord(): Record {
        return Record(
            UUID.randomUUID(),
            Date().toString(),
            "",
            "",
            type
        )
    }

    fun get(id: UUID): Record? {
        return valuesMap[id]
    }

    fun set(record: Record) {
        valuesMap[record.uuid] = record
    }

    fun setAll(records: Map<UUID, Record>) {
        valuesMap.putAll(records)
    }

    fun update(record: Record) {
        valuesMap[record.uuid] = record
    }

    fun size(): Int {
        return valuesMap.size
    }

    fun clearRepository() {
        valuesMap.clear()
    }
}