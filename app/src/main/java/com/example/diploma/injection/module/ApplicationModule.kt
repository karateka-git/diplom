package com.example.diploma.injection.module

import android.content.Context
import androidx.room.Room
import com.example.diploma.db.AppDatabase
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
    fun provideDailyRecordsRepository(appDatabase: AppDatabase): DailyRecordsRepository {
        return DailyRecordsRepository(appDatabase)
    }

    @Singleton
    @Provides
    @JvmStatic
    fun provideHolidayRecordsRepository(): HolidayRecordsRepository {
        return HolidayRecordsRepository()
    }

    @Singleton
    @Provides
    @JvmStatic
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "database").build()
    }
}