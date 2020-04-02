package com.example.diploma.injection.component

import com.example.diploma.base.BaseView
import com.example.diploma.injection.module.ContextModule
import com.example.diploma.injection.module.PresenterModule
import com.example.diploma.ui.main.MainPresenter
import com.example.diploma.ui.record.RecordPresenter
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(ContextModule::class), (PresenterModule::class)])
interface PresenterInjector {
    /**
     * Injects required dependencies into the specified PostPresenter.
     * @param mainPresenter MainPresenter in which to inject the dependencies
     */
    fun inject(mainPresenter: MainPresenter)

    fun inject(recordPresenter: RecordPresenter)

    @Component.Builder
    interface Builder {
        fun build(): PresenterInjector

        fun contextModule(contextModule: ContextModule): Builder

        fun presenterModule(presenterModule: PresenterModule): Builder

        @BindsInstance
        fun baseView(baseView: BaseView): Builder
    }
}