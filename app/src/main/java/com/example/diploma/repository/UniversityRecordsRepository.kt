package com.example.diploma.repository

import com.example.diploma.model.Record
import java.util.*

class UniversityRecordsRepository : IRepository {
    private val repositoryType = "university"
    private val values = mutableMapOf<UUID, Record>()

    init {
        for (i in 0 until 10) {
            val uuid = UUID.randomUUID()
            values[uuid] = Record(uuid,
                Date().toString(),
                "test$i",
                "test$i tester$i testers$i",
                repositoryType)
        }
    }

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