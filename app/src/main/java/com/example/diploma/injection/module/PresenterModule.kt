package com.example.diploma.injection.module
import com.example.diploma.utils.DateAndTimeUtility
import dagger.Module
import dagger.Provides

@Module
object PresenterModule {
    @Provides
    @JvmStatic
    fun provideCalendar(): DateAndTimeUtility {
        return DateAndTimeUtility()
    }
}