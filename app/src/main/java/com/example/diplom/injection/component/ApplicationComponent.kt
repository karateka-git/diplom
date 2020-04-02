package com.example.diplom.injection.component

import com.example.diplom.injection.module.ApplicationModule
import com.example.diplom.repository.UniversityRecordsRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(ApplicationModule::class)])
interface ApplicationComponent {
    fun getUniversityRepository(): UniversityRecordsRepository
}