package com.example.diplom.injection.module

import com.example.diplom.repository.UniversityClassesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object SingletonModule {
    @Singleton
    @Provides
    @JvmStatic
    fun provideUniversityClassesRepository(): UniversityClassesRepository {
        return UniversityClassesRepository()
    }
}