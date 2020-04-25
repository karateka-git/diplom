package com.example.diplom_DP.injection.component

import android.content.Context
import com.example.diplom_DP.db.AppDatabase
import com.example.diplom_DP.injection.module.ApplicationModule
import com.example.diplom_DP.repository.records.RecordsRepository
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