package com.example.diploma.repository.time_tabling

import com.example.diploma.db.entity.RecordEntity
import com.example.diploma.model.Record
import com.example.diploma.utils.exception.CustomException
import java.util.*

interface ITimeTablingRepository {
    /**
     * @return teachers::map<index, name>
     */
    @Throws(CustomException::class)
    fun getTeachers(): Map<String, String>

    @Throws(CustomException::class)
    fun getDate(): Map<Int, String>

    @Throws(CustomException::class)
    fun getSchedule(teacherID: String): Map<UUID, RecordEntity>
}