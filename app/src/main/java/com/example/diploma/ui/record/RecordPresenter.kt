package com.example.diploma.ui.record

import android.content.Context
import android.util.Log
import android.widget.EditText
import com.example.diploma.Constants
import com.example.diploma.MyApplication
import com.example.diploma.base.BasePresenter
import com.example.diploma.db.entity.RecordEntity
import com.example.diploma.model.Record
import com.example.diploma.repository.records.RecordsRepository
import com.example.diploma.utils.DateAndTimeUtility
import javax.inject.Inject

class RecordPresenter(mainView: RecordView) : BasePresenter<RecordView>(mainView)  {
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
    }

    fun datePickerDialog() {
        calendar.showDatePicker(context, view::setDate)
    }

    fun timePickerDialog() {
        calendar.showTimePicker(context, view::setTime)
    }

    fun getEmptyRecord(): RecordEntity {
        return recordsRepository.getEmptyRecord()
    }

    private fun validateField(view: EditText): Boolean {
        return if (view.text.isEmpty()) {
            view.error = Constants.emptyFieldError
            false
        } else {
            true
        }
    }

    fun clickButtonOk() {
        val binding = view.getBinding()

        val validTime = validateField(binding.time)
        val validTitle = validateField(binding.title)
        val validInfo = validateField(binding.info)
        if (
            !validTime or
            !validTitle or
            !validInfo
        ) return

        val record = binding.record!!
        record.apply {
            type = Record.dailyRecord
        }

        Log.d("update", record.toString())
        recordsRepository.update(record)
        view.openMainActivity()
    }
}