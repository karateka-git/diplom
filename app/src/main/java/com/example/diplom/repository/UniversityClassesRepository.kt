package com.example.diplom.repository

import com.example.diplom.model.Record
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

class UniversityClassesRepository : IRepository {
    private val values = mutableMapOf<Int, Record>() //TODO change Collection
    init {
        for (i in 0 until 30) {
            values[i] = Record(i, Date().toString(), "test$i", "test$i tester$i testers$i")
        }
    }

    fun get(id: Int): Record? {
        return values[id]
    }

    fun set(record: Record) {
        values.put(record.id, record)
    }

    fun update(record: Record) {
        values[record.id] = record
    }

    fun getAll(): Map<Int, Record> {
        return values
    }

    fun size(): Int {
        return values.size
    }
}