package com.example.diplom_DP.utils

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import androidx.databinding.InverseMethod
import com.example.diplom_DP.model.Record
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class DateAndTimeUtility {
    companion object {
        private val classTag = DateAndTimeUtility::class.java.simpleName

        const val delimiter = ':'

        @SuppressLint("SimpleDateFormat")
        fun getDateFormatter(): SimpleDateFormat {
            return SimpleDateFormat("dd${delimiter}MM${delimiter}yyyy")
        }

        @SuppressLint("SimpleDateFormat")
        fun getTimeFormatter(): SimpleDateFormat {
            return SimpleDateFormat("kk${delimiter}mm")
        }

        fun toMyDateFormat(string: String): String {
            return string.replace('.', delimiter)
        }

        @InverseMethod("fromDate")
        fun toDate(dateInMillis: Long): String {
            val calendar = Calendar.getInstance().apply {
                timeInMillis = dateInMillis
            }
            return getDateFormatter().format(calendar.time)
        }

        fun fromDate(dateInString: String): Long {
            val arrDate = dateInString.split(delimiter)
            val calendar = Calendar.getInstance().apply {
                set(arrDate[2].toInt(), arrDate[1].toInt() - 1, arrDate[0].toInt())
            }
            return resetTime(calendar).timeInMillis
        }

        fun getCurrentOnlyDate(): Date {
            return resetTime(Calendar.getInstance()).time
        }

        fun resetTime(calendar: Calendar): Calendar {
            return calendar.apply {
                set(Calendar.HOUR_OF_DAY, 0)
                set(Calendar.MINUTE, 0)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }
        }

        fun now(): Calendar {
            return Calendar.getInstance().apply {
                timeInMillis = System.currentTimeMillis()
            }
        }
    }

    private val calendar = Calendar.getInstance().apply {
        timeInMillis = System.currentTimeMillis()
    }

    private fun newDate(day: Int, month: Int, year: Int) {
        calendar.set(year, month, day)
    }

    private fun newTime(hour: Int, minute: Int) {
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
    }

    fun getDateAndTimeForRecord(record: Record): Calendar {
        val arrDate = toDate(record.date)
        newDate(arrDate[0].toInt(), arrDate[1].toInt() - 1, arrDate[2].toInt())
        val arrTime = record.timeFrom.split(delimiter)
        newTime(arrTime[0].toInt(), arrTime[1].toInt())
        return calendar
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
        return getDateFormatter().format(getCurrentOnlyDate())
    }

    fun timeToString(): String {
        return getTimeFormatter().format(getCurrentDate())
    }
}