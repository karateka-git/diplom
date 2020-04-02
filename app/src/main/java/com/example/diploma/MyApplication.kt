package com.example.diploma

import android.app.Application
import com.example.diploma.injection.component.DaggerApplicationComponent
import com.example.diploma.injection.component.ApplicationComponent

class MyApplication : Application() {
    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        this.component = DaggerApplicationComponent.builder()
            .build()
    }
}