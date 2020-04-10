package com.example.diploma.repository.time_tabling

import com.example.diploma.utils.CustomException

interface ITimeTablingRepository {
    /**
     * @return teachers::map<index, name>
     */
    @Throws(CustomException::class)
    fun getTeacher(): Map<String, String>
}