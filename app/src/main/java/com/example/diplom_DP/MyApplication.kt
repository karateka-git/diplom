package com.example.diplom_DP

import android.app.Application
import androidx.core.app.NotificationManagerCompat
import com.example.diplom_DP.injection.component.DaggerApplicationComponent
import com.example.diplom_DP.injection.component.ApplicationComponent
import com.example.diplom_DP.notification.NotificationHelper

class MyApplication : Application() {
    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        this.component = DaggerApplicationComponent.builder()
            .context(applicationContext)
            .build()

        NotificationHelper.createNotificationChannel(this,
            NotificationManagerCompat.IMPORTANCE_DEFAULT, false,
            getString(R.string.app_name), "App notification channel.")

        NotificationHelper.createNotificationChannel(this,
            NotificationManagerCompat.IMPORTANCE_HIGH, false,
            getString(R.string.time_to_record), "Task execution time.")
    }
}