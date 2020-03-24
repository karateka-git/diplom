package com.example.diplom.injection.component

import com.example.diplom.base.BaseView
import com.example.diplom.injection.module.AdapterModule
import com.example.diplom.injection.module.ContextModule
import com.example.diplom.ui.main.MainPresenter
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(ContextModule::class), (AdapterModule::class)])
interface PresenterInjector {
    /**
     * Injects required dependencies into the specified PostPresenter.
     * @param mainPresenter MainPresenter in which to inject the dependencies
     */
    fun inject(mainPresenter: MainPresenter)

    @Component.Builder
    interface Builder {
        fun build(): PresenterInjector

        fun contextModule(contextModule: ContextModule): Builder

        fun notesAdapterModule(notesAdapterModule: AdapterModule): Builder

        @BindsInstance
        fun baseView(baseView: BaseView): Builder
    }
}