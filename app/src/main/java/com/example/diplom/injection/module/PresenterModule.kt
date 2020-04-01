package com.example.diplom.injection.module

import com.example.diplom.utils.MyCalendar
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