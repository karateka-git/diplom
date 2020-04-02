package com.example.diplom.injection.module

import com.example.diplom.repository.UniversityRecordsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object ApplicationModule {
    @Singleton
    @Provides
    @JvmStatic
    fun provideUniversityClassesRepository(): UniversityRecordsRepository {
        return UniversityRecordsRepository()
    }
}