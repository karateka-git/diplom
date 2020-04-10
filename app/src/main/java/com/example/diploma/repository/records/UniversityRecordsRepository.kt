package com.example.diploma.repository.records

import com.example.diploma.model.Record
import java.util.*

class UniversityRecordsRepository :
    IRecordsRepository {
    override val valuesMap = mutableMapOf<UUID, Record>()
    override val type: String
        get() = IRecordsRepository.universityRecordsRepository

    init {
        for (i in 0 until 10) {
            val uuid = UUID.randomUUID()
            valuesMap[uuid] = Record(uuid,
                Date().toString(),
                "test$i",
                "test$i tester$i testers$i",
                type)
        }
    }
}