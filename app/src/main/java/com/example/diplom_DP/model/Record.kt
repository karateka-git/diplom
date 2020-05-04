package com.example.diplom_DP.model

import com.example.diplom_DP.Constants
import java.io.Serializable
import java.util.*

interface Record : Serializable { // TODO use PARCELABLE
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