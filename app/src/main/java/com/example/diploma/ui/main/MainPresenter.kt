package com.example.diploma.ui.main

import android.content.Context
import com.example.diploma.MyApplication
import com.example.diploma.base.BasePresenter
import com.example.diploma.model.Record
import com.example.diploma.repository.records.DailyRecordsRepository
import com.example.diploma.repository.records.HolidayRecordsRepository
import com.example.diploma.repository.records.IRecordsRepository
import com.example.diploma.repository.records.UniversityRecordsRepository
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

    lateinit var dailyRecordsRepository: DailyRecordsRepository

    lateinit var holidayRecordsRepository: HolidayRecordsRepository

    override fun onViewCreated() {
        super.onViewCreated()
        universityRecordsRepository = this.application.component.getUniversityRepository()
        dailyRecordsRepository = this.application.component.getDailyRepository()
        holidayRecordsRepository = this.application.component.getHolidayRepository()
        val mapper = getValues(universityRecordsRepository)
            .plus(getValues(dailyRecordsRepository))
            .plus(getValues(holidayRecordsRepository))
        view.setRecords(mapper)
        view.setDate(calendar.toString())
    }

    private fun getValues(repository: IRecordsRepository): Collection<Record> {
        return repository.valuesMap.values
    }

    fun datePickerDialog() {
        calendar.showDatePicker(context, view::setDate)
    }
}