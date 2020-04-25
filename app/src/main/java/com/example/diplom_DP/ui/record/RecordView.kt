package com.example.diplom_DP.ui.record

import com.example.diplom_DP.base.BaseView
import com.example.diplom_DP.databinding.ActivityRecordBinding

interface RecordView : BaseView {
    fun setDate(date: String)
    fun setTime(time: String)
    fun getBinding(): ActivityRecordBinding
    fun openMainActivity()
}