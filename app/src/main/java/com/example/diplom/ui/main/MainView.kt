package com.example.diplom.ui.main

import com.example.diplom.base.BaseView
import com.example.diplom.model.Record
import com.example.diplom.utils.adapters.RecordsAdapter

interface MainView : BaseView {
    fun setRecords(records: Map<Int, Record>)
    fun setDate(date: String)
}
