package com.example.diploma.repository.records

import com.example.diploma.model.Record
import java.util.*

class DailyRecordsRepository :
    IRecordsRepository {
    override val valuesMap = mutableMapOf<UUID, Record>()
    override val type: String
        get() = IRecordsRepository.dailyRecordsRepository
}