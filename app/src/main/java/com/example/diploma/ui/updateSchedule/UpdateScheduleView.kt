package com.example.diploma.ui.updateSchedule

import com.example.diploma.base.BaseView
import com.example.diploma.utils.IDialogListener

interface UpdateScheduleView : BaseView, IDialogListener {
    fun onFinish()
}