package com.example.diplom.ui.main

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import com.example.diplom.base.BasePresenter
import com.example.diplom.model.Record
import com.example.diplom.utils.MyCalendar
import java.util.*
import javax.inject.Inject

class MainPresenter(mainView: MainView) : BasePresenter<MainView>(mainView) {
    @Inject
    lateinit var context: Context

    @Inject
    lateinit var calendar: MyCalendar

    private val values = mutableListOf<Record>()

    override fun onViewCreated() {
        super.onViewCreated()
        view.setRecords(generateFakeRecords())
        view.setDate(calendar.toString())
    }

    private fun generateFakeRecords(): List<Record> {
        for (i in 0 until 30) {
            values.add(Record(Date(), "test$i", "test$i tester$i testers$i"))
        }
        return values
    }

    fun datePickerDialog() {
        calendar.showDatePicker(context, view::setDate)
    }

    fun getRecord(position: Int): Record {
        return values[position]
    }
}