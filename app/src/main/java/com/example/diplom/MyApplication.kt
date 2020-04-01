package com.example.diplom

import android.app.Application
import com.example.diplom.injection.component.DaggerSingletonComponent
import com.example.diplom.injection.component.SingletonComponent
import kotlinx.android.synthetic.main.activity_main.view.*

class MyApplication : Application() {
    lateinit var component: SingletonComponent

    override fun onCreate() {
        super.onCreate()
        this.component = DaggerSingletonComponent.builder()
            .build()
    }
}