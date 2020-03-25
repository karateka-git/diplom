package com.example.diplom.ui.main

import android.app.DatePickerDialog
import android.content.Context
import com.example.diplom.base.BasePresenter
import com.example.diplom.model.Record
import com.example.diplom.utils.adapters.RecordsAdapter
import java.util.*
import javax.inject.Inject

class MainPresenter(mainView: MainView) : BasePresenter<MainView>(mainView) {
    @Inject
    lateinit var context: Context

    override fun onViewCreated() {
        super.onViewCreated()
        view.setRecords(generateFakeRecords())
    }

    private fun generateFakeRecords(): List<Record> {
        val values = mutableListOf<Record>()
        values.add(Record(Date(), "test1", "test1 tester1 testers1"))
        values.add(Record(Date(), "test2", "test2 tester2 testers2"))
        values.add(Record(Date(), "test3", "test3 tester3 testers3"))
        return values
    }

    fun datePickerDialog() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(context, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

            // Display Selected date in textbox
            lblDate.setText("" + dayOfMonth + " " + MONTHS[monthOfYear] + ", " + year)
        }, year, month, day)

        dpd.show()
    }
}