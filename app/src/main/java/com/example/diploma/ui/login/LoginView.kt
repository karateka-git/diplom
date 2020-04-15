package com.example.diploma.ui.login

import com.example.diploma.base.BaseView
import com.example.diploma.utils.IDialogListener

interface LoginView : BaseView, IDialogListener {
    fun onFinish()
}