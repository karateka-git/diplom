package com.example.diploma.injection.component

import android.content.Context
import com.example.diploma.db.AppDatabase
import com.example.diploma.injection.module.ApplicationModule
import com.example.diploma.repository.records.RecordsRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(ApplicationModule::class)])
interface ApplicationComponent {
    fun getRepository(): RecordsRepository
    fun getAppDatabase(): AppDatabase

    @Component.Builder
    interface Builder {
        fun build() : ApplicationComponent

        @BindsInstance
        fun context(context: Context): Builder
    }
}