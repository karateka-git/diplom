package com.example.diplom_DP.ui.updateSchedule

import android.content.Intent
import android.util.Log
import com.example.diplom_DP.Constants
import com.example.diplom_DP.MyApplication
import com.example.diplom_DP.base.BasePresenter
import com.example.diplom_DP.notification.NotificationHelper
import com.example.diplom_DP.repository.records.RecordsRepository
import com.example.diplom_DP.repository.time_tabling.ITimeTablingRepository
import com.example.diplom_DP.ui.main.MainActivity
import com.example.diplom_DP.utils.NetworkUtils
import com.example.diplom_DP.utils.exception.CustomException
import com.example.diplom_DP.utils.exception.network.InternetConnectionException
import com.example.diplom_DP.utils.exception.network.ServerConnectionException
import javax.inject.Inject

class UpdateSchedulePresenter(updateScheduleView: UpdateScheduleView) : BasePresenter<UpdateScheduleView>(updateScheduleView) {
    private val classTag = UpdateSchedulePresenter::class.java.simpleName

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
            NetworkUtils.showDialog(view.getContext(), view, Constants.checkInternetConnectionMsg)
        } catch(e: ServerConnectionException) {
            Log.e(classTag, "${e.message}")
            NetworkUtils.showDialog(view.getContext(), view, Constants.serverIsNotAvailableMsg)
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
                    view.scheduleUpdateSuccess(teacherName, newSchedule.size)
                    view.onFinish()
                    return
                }
            }
        }
        view.scheduleUpdateFail(teacherName)
    }
}