package com.example.diploma.model

import java.io.Serializable
import java.util.*

interface Record : Serializable {
    companion object Type {
        const val default = "default"
        const val dailyRecord = "daily"
        const val universityRecord = "university"
        const val holidayRecord = "holiday"
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