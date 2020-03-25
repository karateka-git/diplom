package com.example.diplom.utils

import java.util.*

class MyCalendar() {
    private val calendar = Calendar.getInstance()
    var year = calendar.get(Calendar.YEAR)
        private set
    var month= calendar.get(Calendar.MONTH)
        private set
    var day = calendar.get(Calendar.DAY_OF_MONTH)
        private set

    fun newDate(day: Int, month: Int, year: Int) {
        this.day = day
        this.month = month
        this.year = year
    }

    override fun toString(): String {
        return "$day:${month+1}:$year"
    }
}