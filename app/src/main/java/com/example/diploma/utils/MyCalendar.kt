package com.example.diploma.utils

import android.app.DatePickerDialog
import android.content.Context
import java.util.*

class MyCalendar {
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

    fun showDatePicker(context: Context, callback: (date: String) -> Unit ) {

        val callback_wrap = DatePickerDialog.OnDateSetListener{ _, y, m, d ->
            newDate(d,m,y)
            callback(this.toString())
        }

        DatePickerDialog(context, callback_wrap, year, month, day).show()
    }

    override fun toString(): String {
        return "$day:${month+1}:$year"
    }
}