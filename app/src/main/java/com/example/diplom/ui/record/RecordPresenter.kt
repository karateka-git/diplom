package com.example.diplom.ui.record

import android.content.Context
import android.widget.EditText
import com.example.diplom.MyApplication
import com.example.diplom.base.BasePresenter
import com.example.diplom.model.Record
import com.example.diplom.repository.UniversityRecordsRepository
import com.example.diplom.utils.MyCalendar
import javax.inject.Inject

class RecordPresenter(mainView: RecordView) : BasePresenter<RecordView>(mainView)  {
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
    }

    fun datePickerDialog() {
        calendar.showDatePicker(context, view::setDate)
    }

    fun getEmptyRecord(): Record {
        return universityRecordsRepository.createRecord()
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
        //TODO Check Record field
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

        universityRecordsRepository.update(record)
        view.clickButtonOk()
    }
}