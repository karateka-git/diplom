package com.example.diploma.model

import com.example.diploma.Constants
import java.io.Serializable
import java.util.*

interface Record : Serializable {
    companion object Type {
        const val default = Constants.defaultType
        const val dailyRecord = Constants.dailyRecordType
        const val universityRecord = Constants.universityRecordType
        const val holidayRecord = Constants.holidayRecordType
    }

    val uuid: UUID
    var date: String
    var timeFrom: String
    var timeTo: String
    var title: String
    var info: String
    var type: String
    var isCompleted: Boolean
}