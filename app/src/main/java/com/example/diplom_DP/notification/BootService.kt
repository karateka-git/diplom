package com.example.diplom_DP.notification

import android.app.IntentService
import android.content.Intent
import android.util.Log
import com.example.diplom_DP.injection.component.DaggerApplicationComponent
import com.example.diplom_DP.utils.DateAndTimeUtility

class BootService : IntentService("BootService") {
    val TAG = "Boot"
    override fun onHandleIntent(intent: Intent?) {
        val component = DaggerApplicationComponent.builder()
            .context(applicationContext)
            .build()
        val repository = component.getRepository()
        val date = DateAndTimeUtility
        val records = repository.getRecordsForBootService(date.dateToString())
        Log.d(TAG, "current date - ${date.dateToString()}")
        for ((key, record) in records) {
            Log.d(TAG, "$key record from repository - $record")
        }
    }
}