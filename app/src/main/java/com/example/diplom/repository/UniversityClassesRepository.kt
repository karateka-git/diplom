package com.example.diplom.repository

import com.example.diplom.model.Record
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

class UniversityClassesRepository : IRepository {
    private val values = mutableMapOf<UUID, Record>() //TODO change Collection
    init {
        for (i in 0 until 10) {
            val uuid = UUID.randomUUID()
            values[uuid] = Record(uuid, Date().toString(), "test$i", "test$i tester$i testers$i")
        }
    }

    fun createRecord(): Record {
        return Record(
            UUID.randomUUID(),
            "",
            "",
            ""
        )
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