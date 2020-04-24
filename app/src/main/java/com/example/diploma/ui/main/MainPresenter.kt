package com.example.diploma.ui.main

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.diploma.MyApplication
import com.example.diploma.base.BasePresenter
import com.example.diploma.db.entity.RecordEntity
import com.example.diploma.model.Record
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

    private lateinit var recordsRepository: RecordsRepository

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

    fun getValues(): LiveData<List<RecordEntity>> {
        return recordsRepository.valuesMap
    }

    fun setSelectedRecords(selected: Int) {
        recordsRepository.setSelectedRecords(selected)
    }

    /**
     * Set selected Date in repository and updates recycle view values
     */
    fun setDate(date: String) {
        recordsRepository.selectedDate = date
        recordsRepository.setSelectedRecords(view.getCurrentIndexSelectedRecords())
    }

    fun completedChange(record: Record) {
        record.isCompleted = record.isCompleted.not()
        recordsRepository.update(record as RecordEntity)
    }
}