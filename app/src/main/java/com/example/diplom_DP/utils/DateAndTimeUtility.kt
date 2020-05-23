package com.example.diplom_DP.utils

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import androidx.databinding.InverseMethod
import com.example.diplom_DP.model.Record
import java.text.SimpleDateFormat
import java.util.*

object DateAndTimeUtility {
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

    fun resetTime(calendar: Calendar): Calendar {
        return calendar.apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
    }

    private fun now(): Calendar {
        return Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
        }
    }

    private fun newDate(day: Int, month: Int, year: Int, hour: Int, minute: Int): Calendar {
        val calendar = now()
        calendar.set(year, month, day, hour, minute)
        return calendar
    }

    fun getTimeForAlarm(record: Record): Calendar {
        val arrDate = toDate(record.date).split(delimiter)
        val arrTime = record.timeFrom.split(delimiter)
        return newDate(arrDate[0].toInt(), arrDate[1].toInt() - 1, arrDate[2].toInt(), arrTime[0].toInt(), arrTime[1].toInt())
    }

    fun showTimePicker(context: Context, callback: (time: String) -> Unit) {
        val calendar = now()
        val year = calendar.get(Calendar.YEAR)
        val month= calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val callbackWrap = TimePickerDialog.OnTimeSetListener { _, h, m ->
            callback(timeToString(newDate(day, month, year, h, m)))
        }

        TimePickerDialog(context, callbackWrap, hour, minute, true).show()
    }

    fun showDatePicker(context: Context, currentDate: String, callback: (date: String) -> Unit ) {
        val calendar = Calendar.getInstance().apply {
            timeInMillis = fromDate(currentDate)
        }
        val year = calendar.get(Calendar.YEAR)
        val month= calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val callbackWrap = DatePickerDialog.OnDateSetListener{ _, y, m, d ->
            callback(dateToString(newDate(d,m,y, 0, 0)))
        }

        DatePickerDialog(context, callbackWrap, year, month, day).show()
    }

    fun dateToString(calendar: Calendar = now()): String {
        return getDateFormatter().format(resetTime(calendar).time)
    }

    fun timeToString(calendar: Calendar = now()): String {
        return getTimeFormatter().format(calendar.time)
    }
}