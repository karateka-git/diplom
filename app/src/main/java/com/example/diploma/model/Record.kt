package com.example.diploma.model

import com.example.diploma.repository.records.IRecordsRepository
import java.io.Serializable
import java.util.*

data class Record (val id: UUID,
                   var date: String,
                   var title: String,
                   var info: String,
                   var type: String = IRecordsRepository.default
    ): Serializable