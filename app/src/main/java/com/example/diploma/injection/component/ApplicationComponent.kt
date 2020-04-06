package com.example.diploma.injection.component

import com.example.diploma.injection.module.ApplicationModule
import com.example.diploma.repository.DailyRecordsRepository
import com.example.diploma.repository.HolidayRecordsRepository
import com.example.diploma.repository.UniversityRecordsRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(ApplicationModule::class)])
interface ApplicationComponent {
    fun getUniversityRepository(): UniversityRecordsRepository
    fun getDailyRepository(): DailyRecordsRepository
    fun getHolidayRepository(): HolidayRecordsRepository
}