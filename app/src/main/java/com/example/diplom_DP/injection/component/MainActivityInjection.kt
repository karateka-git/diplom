package com.example.diplom_DP.injection.component

import com.example.diplom_DP.injection.module.RecordsAdapterModule
import com.example.diplom_DP.ui.main.MainActivity
import com.example.diplom_DP.utils.adapters.RecordsAdapter
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