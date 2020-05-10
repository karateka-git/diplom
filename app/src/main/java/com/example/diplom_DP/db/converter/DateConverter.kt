package com.example.diplom_DP.db.converter

import androidx.room.TypeConverter
import com.example.diplom_DP.utils.DateAndTimeUtility

class DateConverter {
    @TypeConverter
    fun fromDate(date: String): Long {
        return DateAndTimeUtility.fromDate(date)
    }

    @TypeConverter
    fun toDate(dateInMillis: Long): String {
        return DateAndTimeUtility.toDate(dateInMillis)
    }
}