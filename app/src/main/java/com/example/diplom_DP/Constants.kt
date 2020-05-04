package com.example.diplom_DP

import java.lang.Package.getPackage

class Constants {
    companion object {
        // Servers
        const val internetConnectionTestServer = "https://yandex.ru/"
        const val xmlScheduleServer = "http://192.168.0.101"
        const val xmlSchedulePath = "$xmlScheduleServer/schedule.xml"

        // Strings
        const val checkInternetConnectionMsg = "Check your Internet Connection."
        const val serverIsNotAvailableMsg = "Sorry, server is not available."
        const val networkErrorTitle = "Network Error"
        const val buttonOk = "Ok"
        const val emptyFieldError = "Empty field!"

        // Schedule parse String
        const val tagNameTeacher = "Teacher"
        const val attributeNameIndex = "Index"
        const val attributeNameTeacherName = "Name"
        const val tagNameTimeTabling = "UniversityTimeTabling"
        const val attributeNameDate = "Date"
        const val tagNameStringOfSchedule = "StringOfSchedule"
        const val tagNameTimeWindow = "TimeWindow"
        const val attributeNameTimeWindow = "NumberOfWindow"
        const val attributeNameTeacher = tagNameTeacher
        const val attributeNameTimeFrom = "From"
        const val attributeNameTimeTo = "To"
        const val attributeNameGroup = "Group"
        const val attributeNameDiscipline = "Discipline"

        // Record Type
        const val defaultType = "default"
        const val dailyRecordType = "daily"
        const val universityRecordType = "university"
        const val holidayRecordType = "holiday"

        // Action
        const val actionShowRecord = "com.example.diplom_DP.showRecord"

        // Extra key
        const val id_key = "id"
    }
}