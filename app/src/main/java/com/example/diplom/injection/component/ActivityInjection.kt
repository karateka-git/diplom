package com.example.diplom.injection.component

import com.example.diplom.base.BaseView
import com.example.diplom.injection.module.ContextModule
import com.example.diplom.injection.module.RecordsAdapterModule
import com.example.diplom.ui.main.MainActivity
import com.example.diplom.utils.adapters.RecordsAdapter
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(ContextModule::class), (RecordsAdapterModule::class)])
interface ActivityInjector {

    fun inject(mainActivity: MainActivity)

    @Component.Builder
    interface Builder {
        fun build(): ActivityInjector

        fun contextModule(contextModule: ContextModule): Builder

        fun recordsAdapterModule(recordsAdapterModule: RecordsAdapterModule): Builder

        @BindsInstance
        fun baseView(baseView: BaseView): Builder
    }
}