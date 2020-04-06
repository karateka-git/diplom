package com.example.diploma.ui.main

import com.example.diploma.base.BaseView
import com.example.diploma.model.Record
import java.util.*

interface MainView : BaseView {
    fun setRecords(records: List<Record>)
    fun setDate(date: String)
}
