package com.example.diploma.injection.component

import com.example.diploma.injection.module.RecordsAdapterModule
import com.example.diploma.ui.main.MainActivity
import com.example.diploma.utils.adapters.RecordsAdapter
import dagger.BindsInstance
import dagger.Component

@Component(modules = [(RecordsAdapterModule::class)])
interface MainActivityInjector {

    fun inject(mainActivity: MainActivity)

    @Component.Builder
    interface Builder {
        fun build(): MainActivityInjector

        @BindsInstance
        fun listener(listener: RecordsAdapter.OnRecordListener): Builder
    }
}