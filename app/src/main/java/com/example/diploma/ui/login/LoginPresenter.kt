package com.example.diploma.ui.login

import android.content.Intent
import android.util.Log
import com.example.diploma.MyApplication
import com.example.diploma.base.BasePresenter
import com.example.diploma.repository.records.RecordsRepository
import com.example.diploma.repository.time_tabling.ITimeTablingRepository
import com.example.diploma.ui.main.MainActivity
import com.example.diploma.utils.NetworkUtils
import com.example.diploma.utils.exception.CustomException
import com.example.diploma.utils.exception.network.InternetConnectionException
import com.example.diploma.utils.exception.network.ServerConnectionException
import javax.inject.Inject

class LoginPresenter(loginView: LoginView) : BasePresenter<LoginView>(loginView) {
    private val classTag = LoginPresenter::class.java.simpleName

    @Inject
    lateinit var xmlTimeTablingRepository: ITimeTablingRepository

    @Inject
    lateinit var application: MyApplication

    lateinit var recordsRepository: RecordsRepository

    var teachers = mapOf<String, String>()

    override fun onViewCreated() {
        super.onViewCreated()

        recordsRepository = this.application.component.getRepository()

        // TODO to much long, mb break down, FIX!

        try {
            teachers = xmlTimeTablingRepository.getTeachers()
            val date = xmlTimeTablingRepository.getDate()[0] ?: error("")
            Log.d(classTag, date)
        } catch(e: CustomException) {
            Log.e(classTag, "${e.message}")
        } catch(e: InternetConnectionException) {
            Log.e(classTag, "${e.message}")
            NetworkUtils.showDialog(view.getContext(), view, "Check your Internet Connection.")
        } catch(e: ServerConnectionException) {
            Log.e(classTag, "${e.message}")
            NetworkUtils.showDialog(view.getContext(), view, "Sorry, server is not available.")
        }
    }

    fun getTeachers(): List<String> {
        return teachers.values.toList()
    }

    fun clickButtonOk(teacherName: String) {
        for ((key, element) in teachers) {
            if (element == teacherName) {
                val newSchedule = xmlTimeTablingRepository.getSchedule(key)
                if (newSchedule.isNotEmpty()) {
//                    recordsRepository.clearRepository()
                    recordsRepository.updateUniversityRecords(newSchedule)
                    Log.d(classTag, "find")
                    val intent = Intent(view.getContext(), MainActivity::class.java)
                    view.getContext().startActivity(intent)
                    return
                }
            }
        }
        Log.d(classTag, "not find, teacher name - $teacherName")
    }
}