package com.example.diplom_DP.injection.module

import android.content.Context
import androidx.room.Room
import com.example.diplom_DP.db.AppDatabase
import com.example.diplom_DP.repository.records.RecordsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object ApplicationModule {

    @Singleton
    @Provides
    @JvmStatic
    fun provideDailyRecordsRepository(appDatabase: AppDatabase): RecordsRepository {
        return RecordsRepository(appDatabase)
    }

    @Singleton
    @Provides
    @JvmStatic
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "database")
            .fallbackToDestructiveMigration() //TODO deleted this instruction
            .build()
    }
}