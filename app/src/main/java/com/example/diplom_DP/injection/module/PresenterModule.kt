package com.example.diplom_DP.injection.module
import com.example.diplom_DP.utils.DateAndTimeUtility
import dagger.Module
import dagger.Provides

@Module
object PresenterModule {
    @Provides
    @JvmStatic
    fun provideCalendar(): DateAndTimeUtility {
        return DateAndTimeUtility
    }
}