package com.example.diploma.repository.time_tabling

import com.example.diploma.utils.CustomException

interface ITimeTablingRepository {
    /**
     * @return teachers::map<index, name>
     */
    @Throws(CustomException::class)
    fun getTeachers(): Map<String, String>

    @Throws(CustomException::class)
    fun getDate(): Map<String, String>

    @Throws(CustomException::class)
    fun getSchedule(): Map<String, String>
}