package com.example.diploma.repository

import com.example.diploma.model.Record
import java.util.*

interface IRepository {
    fun getEmptyRecord(): Record {
        return Record(
            UUID.randomUUID(),
            Date().toString(),
            "",
            ""
        )
    }
}