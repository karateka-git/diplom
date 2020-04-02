package com.example.diploma.ui.main

import android.content.Context
import com.example.diploma.MyApplication
import com.example.diploma.base.BasePresenter
import com.example.diploma.repository.UniversityRecordsRepository
import com.example.diploma.utils.MyCalendar
import javax.inject.Inject

class MainPresenter(mainView: MainView) : BasePresenter<MainView>(mainView) {
    @Inject
    lateinit var context: Context

    @Inject
    lateinit var calendar: MyCalendar

    @Inject
    lateinit var application: MyApplication

    lateinit var universityRecordsRepository: UniversityRecordsRepository

    override fun onViewCreated() {
        super.onViewCreated()
        universityRecordsRepository = this.application.component.getUniversityRepository()
        view.setRecords(universityRecordsRepository.getAll())
        view.setDate(calendar.toString())
    }

    fun datePickerDialog() {
        calendar.showDatePicker(context, view::setDate)
    }
}