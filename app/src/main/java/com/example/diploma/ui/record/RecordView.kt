package com.example.diploma.ui.record

import com.example.diploma.base.BaseView
import com.example.diploma.databinding.ActivityRecordBinding

interface RecordView : BaseView {
    fun setDate(date: String)
    fun setTime(time: String)
    fun getBinding(): ActivityRecordBinding
    fun openMainActivity()
}