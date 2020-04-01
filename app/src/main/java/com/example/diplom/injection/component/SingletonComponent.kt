package com.example.diplom.injection.component

import com.example.diplom.injection.module.SingletonModule
import com.example.diplom.repository.UniversityClassesRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(SingletonModule::class)])
interface SingletonComponent {
    fun getUniversityRepository(): UniversityClassesRepository
}