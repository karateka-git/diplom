package com.example.diploma.injection.module

import com.example.diploma.repository.records.DailyRecordsRepository
import com.example.diploma.repository.records.HolidayRecordsRepository
import com.example.diploma.repository.records.UniversityRecordsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object ApplicationModule {
    @Singleton
    @Provides
    @JvmStatic
    fun provideUniversityRecordsRepository(): UniversityRecordsRepository {
        return UniversityRecordsRepository()
    }

    @Singleton
    @Provides
    @JvmStatic
    fun provideDailyRecordsRepository(): DailyRecordsRepository {
        return DailyRecordsRepository()
    }

    @Singleton
    @Provides
    @JvmStatic
    fun provideHolidayRecordsRepository(): HolidayRecordsRepository {
        return HolidayRecordsRepository()
    }
}