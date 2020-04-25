package com.example.diplom_DP.utils

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import java.text.SimpleDateFormat
import java.util.*

class DateAndTimeUtility {
    companion object {
        private val classTag = DateAndTimeUtility::class.java.simpleName

        @SuppressLint("SimpleDateFormat")
        fun getDateFormatter(): SimpleDateFormat {
            return SimpleDateFormat("dd:MM:yyyy")
        }

        @SuppressLint("SimpleDateFormat")
        fun getTimeFormatter(): SimpleDateFormat {
            return SimpleDateFormat("kk:mm")
        }

        fun toMyDateFormat(string: String): String {
            return string.replace('.', ':')
        }
    }

    private val calendar = Calendar.getInstance()

    private fun newDate(day: Int, month: Int, year: Int) {
        calendar.set(year, month, day)
    }

    private fun newTime(hour: Int, minute: Int) {
        calendar.set(Calendar.HOUR, hour)
        calendar.set(Calendar.MINUTE, minute)
    }

    fun showTimePicker(context: Context, callback: (time: String) -> Unit) {
        val hour = calendar.get(Calendar.HOUR)
        val minute = calendar.get(Calendar.MINUTE)

        val callbackWrap = TimePickerDialog.OnTimeSetListener { _, h, m ->
            newTime(h, m)
            callback(this.timeToString())
        }

        TimePickerDialog(context, callbackWrap, hour, minute, true).show()
    }

    fun showDatePicker(context: Context, callback: (date: String) -> Unit ) {
        val year = calendar.get(Calendar.YEAR)
        val month= calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val callbackWrap = DatePickerDialog.OnDateSetListener{ _, y, m, d ->
            newDate(d,m,y)
            callback(this.dateToString())
        }

        DatePickerDialog(context, callbackWrap, year, month, day).show()
    }

    private fun getCurrentDate(): Date {
        return calendar.time
    }

    fun dateToString(): String {
        return getDateFormatter().format(getCurrentDate())
    }

    fun timeToString(): String {
        return getTimeFormatter().format(getCurrentDate())
    }
}