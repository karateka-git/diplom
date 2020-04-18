package com.example.diploma.db.converter

import androidx.room.TypeConverter
import java.util.*

class UUIDConverter {
    @TypeConverter
    fun fromUUID(uuid: UUID): String {
        return uuid.toString()
    }

    @TypeConverter
    fun toUUID(string: String): UUID {
        return UUID.fromString(string)
    }
}