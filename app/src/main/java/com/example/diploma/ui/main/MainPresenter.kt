package com.example.diploma.ui.main

import android.content.Context
import android.util.Log
import com.example.diploma.MyApplication
import com.example.diploma.base.BasePresenter
import com.example.diploma.model.Record
import com.example.diploma.repository.records.DailyRecordsRepository
import com.example.diploma.repository.records.HolidayRecordsRepository
import com.example.diploma.repository.records.IRecordsRepository
import com.example.diploma.repository.records.UniversityRecordsRepository
import com.example.diploma.repository.time_tabling.ITimeTablingRepository
import com.example.diploma.repository.time_tabling.XmlTimeTablingRepository
import com.example.diploma.utils.CustomException
import com.example.diploma.utils.MyCalendar
import javax.inject.Inject

class MainPresenter(mainView: MainView) : BasePresenter<MainView>(mainView) {
    @Inject
    lateinit var context: Context

    @Inject
    lateinit var calendar: MyCalendar

    @Inject
    lateinit var application: MyApplication

    @Inject
    lateinit var xmlTimeTablingRepository: ITimeTablingRepository

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

        try {
            val answer: Map<String, String> = xmlTimeTablingRepository.getTeacher()
            Log.e("Teacher_size", answer.size.toString())
            for ((key, value) in answer) {
                Log.e("Teacher_main", value)
            }
        } catch(e: CustomException) {
            Log.e("Exception", "${e.message}")
        }
    }

    private fun getValues(repository: IRecordsRepository): Collection<Record> {
        return repository.valuesMap.values
    }

    fun datePickerDialog() {
        calendar.showDatePicker(context, view::setDate)
    }
}