package com.example.diplom_DP.repository.time_tabling

import com.example.diplom_DP.db.entity.RecordEntity
import com.example.diplom_DP.utils.exception.CustomException
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