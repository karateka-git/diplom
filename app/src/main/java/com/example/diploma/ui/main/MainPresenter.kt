package com.example.diploma.ui.main

import android.content.Context
import com.example.diploma.MyApplication
import com.example.diploma.base.BasePresenter
import com.example.diploma.repository.records.RecordsRepository
import com.example.diploma.utils.DateAndTimeUtility
import javax.inject.Inject

class MainPresenter(mainView: MainView) : BasePresenter<MainView>(mainView) {
    @Inject
    lateinit var context: Context

    @Inject
    lateinit var calendar: DateAndTimeUtility

    @Inject
    lateinit var application: MyApplication

    lateinit var recordsRepository: RecordsRepository

    override fun onViewCreated() {
        super.onViewCreated()
        recordsRepository = this.application.component.getRepository()
        val currentDate = calendar.dateToString()
        view.setDate(currentDate)
        recordsRepository.selectedDate = currentDate
    }

    fun datePickerDialog() {
        calendar.showDatePicker(context, view::setDate)
    }
}