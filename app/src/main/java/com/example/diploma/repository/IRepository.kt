package com.example.diploma.repository

import com.example.diploma.model.Record
import java.util.*

interface IRepository {
    companion object {
        const val default = "default"
        const val dailyRecordsRepository = "daily"
        const val universityRecordsRepository = "university"
    }
    fun getEmptyRecord(): Record {
        return Record(
            UUID.randomUUID(),
            Date().toString(),
            "",
            ""
        )
    }
}