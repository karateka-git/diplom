package com.example.diplom.ui.record

import com.example.diplom.base.BaseView
import com.example.diplom.databinding.ActivityRecordBinding
import com.example.diplom.model.Record

interface RecordView : BaseView {
    fun setDate(date: String)
    fun getBinding(): ActivityRecordBinding
    fun clickButtonOk()
}