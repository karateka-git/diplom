package com.example.diplom.ui.main

import com.example.diplom.base.BaseView
import com.example.diplom.model.Record
import com.example.diplom.utils.adapters.RecordsAdapter
import java.util.*

interface MainView : BaseView {
    fun setRecords(records: Map<UUID, Record>)
    fun setDate(date: String)
}
