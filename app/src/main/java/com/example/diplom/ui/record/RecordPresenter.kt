package com.example.diplom.ui.record

import android.content.Context
import com.example.diplom.base.BasePresenter
import com.example.diplom.utils.MyCalendar
import javax.inject.Inject

class RecordPresenter(mainView: RecordView) : BasePresenter<RecordView>(mainView)  {
    @Inject
    lateinit var context: Context

    @Inject
    lateinit var calendar: MyCalendar

    fun datePickerDialog() {
        calendar.showDatePicker(context, view::setDate)
    }
}