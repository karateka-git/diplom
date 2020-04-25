package com.example.diplom_DP.ui.updateSchedule

import com.example.diplom_DP.base.BaseView
import com.example.diplom_DP.utils.IDialogListener

interface UpdateScheduleView : BaseView, IDialogListener {
    fun onFinish()
    fun scheduleUpdateSuccess(teacher: String, size: Int)
    fun scheduleUpdateFail(teacher: String)
}