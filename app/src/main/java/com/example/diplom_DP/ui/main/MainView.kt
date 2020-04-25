package com.example.diplom_DP.ui.main

import com.example.diplom_DP.base.BaseView

interface MainView : BaseView {
    fun setDate(date: String)
    fun getCurrentIndexSelectedRecords(): Int
}
