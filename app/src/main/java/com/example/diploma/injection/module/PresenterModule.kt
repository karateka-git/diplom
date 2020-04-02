package com.example.diploma.injection.module

import com.example.diploma.utils.MyCalendar
import dagger.Module
import dagger.Provides

@Module
object PresenterModule {
    @Provides
    @JvmStatic
    fun provideCalendar(): MyCalendar {
        return MyCalendar()
    }
}