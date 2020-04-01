package com.example.diplom.repository

import com.example.diplom.model.Record
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

class UniversityClassesRepository : IRepository {
    private val values = mutableListOf<Record>() //TODO change Collection
    init {
        for (i in 0 until 30) {
            values.add(Record(i, Date().toString(), "test$i", "test$i tester$i testers$i"))
        }
    }

    fun get(id: Int): Record {
        return values.first { it -> it.id == id }
    }

    fun set(record: Record) {
        values.add(record)
    }

    fun update(record: Record) {
        values[values.indexOf(get(record.id))] = record
    }

    fun getAll(): List<Record> {
        return values
    }

    fun size(): Int {
        return values.size
    }
}