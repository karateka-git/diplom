package com.example.diploma.ui.record

import android.content.Context
import android.widget.EditText
import com.example.diploma.MyApplication
import com.example.diploma.base.BasePresenter
import com.example.diploma.model.Record
import com.example.diploma.repository.records.DailyRecordsRepository
import com.example.diploma.utils.MyCalendar
import javax.inject.Inject

class RecordPresenter(mainView: RecordView) : BasePresenter<RecordView>(mainView)  {
    @Inject
    lateinit var context: Context

    @Inject
    lateinit var calendar: MyCalendar

    @Inject
    lateinit var application: MyApplication

    private lateinit var dailyRecordsRepository: DailyRecordsRepository

    override fun onViewCreated() {
        super.onViewCreated()
        dailyRecordsRepository = this.application.component.getDailyRepository()
    }

    fun datePickerDialog() {
        calendar.showDatePicker(context, view::setDate)
    }

    fun getEmptyRecord(): Record {
        return dailyRecordsRepository.getEmptyRecord()
    }

    private fun validateField(view: EditText): Boolean {
        return if (view.text.isEmpty()) {
            view.error = "Empty field!"
            false
        } else {
            true
        }
    }

    fun clickButtonOk() {
        val binding = view.getBinding()
        val id = binding.record!!.id
        if (
            !validateField(binding.title) or
            !validateField(binding.info)
        ) return


        val record = Record(
            id,
            binding.date.text.toString(),
            binding.title.text.toString(),
            binding.info.text.toString()
        )

        dailyRecordsRepository.update(record)
        view.clickButtonOk()
    }
}