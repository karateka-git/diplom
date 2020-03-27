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

    override fun onViewCreated() {
        super.onViewCreated()
        view.setRecords(generateFakeRecords())
        view.setDate(calendar.toString())
    }

    private fun generateFakeRecords(): List<Record> {
        val values = mutableListOf<Record>()
        for (i in 0 until 30) {
            values.add(Record(Date(), "test$i", "test$i tester$i testers$i"))
        }
        return values
    }

    fun datePickerDialog() {
        val year = calendar.year
        val month = calendar.month
        val day = calendar.day

        val callback = DatePickerDialog.OnDateSetListener{  v, y, m, d ->
            calendar.newDate(d,m,y)
            view.setDate(calendar.toString())
        }

        DatePickerDialog(context, callback, year, month, day).show()
    }
}