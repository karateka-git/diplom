package com.example.diplom.ui.main

import android.app.Application
import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import com.example.diplom.MyApplication
import com.example.diplom.base.BasePresenter
import com.example.diplom.model.Record
import com.example.diplom.repository.UniversityClassesRepository
import com.example.diplom.utils.MyCalendar
import java.util.*
import javax.inject.Inject

class MainPresenter(mainView: MainView) : BasePresenter<MainView>(mainView) {
    @Inject
    lateinit var context: Context

    @Inject
    lateinit var calendar: MyCalendar

    @Inject
    lateinit var application: MyApplication

    lateinit var universityClassesRepository: UniversityClassesRepository

    override fun onViewCreated() {
        super.onViewCreated()
        universityClassesRepository = this.application.component.getUniversityRepository()
        view.setRecords(universityClassesRepository.getAll())
        view.setDate(calendar.toString())
    }

    fun datePickerDialog() {
        calendar.showDatePicker(context, view::setDate)
    }

    fun getRecord(position: Int): Record {
        return universityClassesRepository.get(position)
    }
}