package com.example.diploma.ui.login

import android.util.Log
import com.example.diploma.base.BasePresenter
import com.example.diploma.repository.time_tabling.ITimeTablingRepository
import com.example.diploma.utils.CustomException
import javax.inject.Inject

class LoginPresenter(loginView: LoginView) : BasePresenter<LoginView>(loginView) {
    @Inject
    lateinit var xmlTimeTablingRepository: ITimeTablingRepository

    fun getTeachers(): List<String> {
        // TODO to much long
        var answer = mapOf<String, String>()
        try {
            answer = xmlTimeTablingRepository.getTeachers()
        } catch(e: CustomException) {
            Log.e("Exception", "${e.message}")
        } finally {
            return answer.values.toList()
        }
    }
}