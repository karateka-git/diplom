package com.example.diplom

import android.app.Application
import com.example.diplom.injection.component.DaggerApplicationComponent
import com.example.diplom.injection.component.ApplicationComponent

class MyApplication : Application() {
    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        this.component = DaggerApplicationComponent.builder()
            .build()
    }
}