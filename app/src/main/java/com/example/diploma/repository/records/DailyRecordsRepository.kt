package com.example.diploma.repository.records

import com.example.diploma.db.AppDatabase
import com.example.diploma.model.Record
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.util.*

class DailyRecordsRepository(val appDatabase: AppDatabase) :
    IRecordsRepository {
    override val valuesMap = mutableMapOf<UUID, Record>()
    override val type: String
        get() = IRecordsRepository.dailyRecordsRepository

    init {
        runBlocking(Dispatchers.IO) {
            for (element in appDatabase.recordDao.getAll()) {
                valuesMap.put(element.uuid, element)
            }
        }
    }

    override fun set(record: Record) {
        runBlocking(Dispatchers.IO) {
            appDatabase.recordDao.insert(record)
        }
    }

    override fun update(record: Record) {
        runBlocking(Dispatchers.IO) {
            appDatabase.recordDao.insert(record)
        }
    }
}