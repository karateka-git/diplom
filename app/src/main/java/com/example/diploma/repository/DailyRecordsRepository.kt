package com.example.diploma.repository

import com.example.diploma.model.Record
import java.util.*

class DailyRecordsRepository : IRepository  {
    private val repositoryType = IRepository.dailyRecordsRepository
    private val values = mutableMapOf<UUID, Record>()

    override fun getEmptyRecord(): Record {
        return super.getEmptyRecord().apply {
            this.type = repositoryType
        }
    }

    fun get(id: UUID): Record? {
        return values[id]
    }

    fun set(record: Record) {
        values[record.id] = record
    }

    fun update(record: Record) {
        values[record.id] = record
    }

    fun getAll(): Map<UUID, Record> {
        return values
    }

    fun size(): Int {
        return values.size
    }
}