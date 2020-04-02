package com.example.diplom.ui.record

import android.content.Context
import android.os.Bundle
import com.example.diplom.MyApplication
import com.example.diplom.base.BasePresenter
import com.example.diplom.databinding.ActivityRecordBinding
import com.example.diplom.model.Record
import com.example.diplom.repository.UniversityClassesRepository
import com.example.diplom.utils.MyCalendar
import java.text.FieldPosition
import java.util.*
import javax.inject.Inject

class RecordPresenter(mainView: RecordView) : BasePresenter<RecordView>(mainView)  {
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
    }

    fun datePickerDialog() {
        calendar.showDatePicker(context, view::setDate)
    }

    fun getEmptyRecord(): Record {
        return universityClassesRepository.createRecord()
    }

    fun clickButtonOk() {
        val binding = view.getBinding()
        //TODO Check Record field
        val id = binding.record!!.id
        val record = Record(
            id,
            binding.date.text.toString(),
            binding.title.text.toString(),
            binding.info.text.toString()
        )
        universityClassesRepository.update(record)
        view.clickButtonOk()
    }
}