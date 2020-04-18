package com.example.diploma.injection.component

import android.content.Context
import com.example.diploma.db.AppDatabase
import com.example.diploma.injection.module.ApplicationModule
import com.example.diploma.injection.module.ContextModule
import com.example.diploma.repository.records.DailyRecordsRepository
import com.example.diploma.repository.records.HolidayRecordsRepository
import com.example.diploma.repository.records.UniversityRecordsRepository
import com.example.diploma.ui.main.MainPresenter
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(ApplicationModule::class)])
interface ApplicationComponent {
    fun getUniversityRepository(): UniversityRecordsRepository
    fun getDailyRepository(): DailyRecordsRepository
    fun getHolidayRepository(): HolidayRecordsRepository
    fun getAppDatabase(): AppDatabase

    @Component.Builder
    interface Builder {
        fun build() : ApplicationComponent

        @BindsInstance
        fun context(context: Context): Builder
    }
}