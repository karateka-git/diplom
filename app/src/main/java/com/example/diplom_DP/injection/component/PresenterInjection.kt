package com.example.diplom_DP.injection.component

import com.example.diplom_DP.base.BaseView
import com.example.diplom_DP.injection.module.ContextModule
import com.example.diplom_DP.injection.module.PresenterModule
import com.example.diplom_DP.injection.module.TimeTablingModule
import com.example.diplom_DP.ui.updateSchedule.UpdateSchedulePresenter
import com.example.diplom_DP.ui.main.MainPresenter
import com.example.diplom_DP.ui.record.RecordPresenter
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(ContextModule::class), (PresenterModule::class), (TimeTablingModule::class)])
interface PresenterInjector {
    /**
     * Injects required dependencies into the specified PostPresenter.
     * @param mainPresenter MainPresenter in which to inject the dependencies
     */
    fun inject(mainPresenter: MainPresenter)

    fun inject(recordPresenter: RecordPresenter)

    fun inject(updateSchedulePresenter: UpdateSchedulePresenter)

    @Component.Builder
    interface Builder {
        fun build(): PresenterInjector

        fun contextModule(contextModule: ContextModule): Builder

        fun presenterModule(presenterModule: PresenterModule): Builder

        fun timeTablingModule(timeTablingModule: TimeTablingModule): Builder

        @BindsInstance
        fun baseView(baseView: BaseView): Builder
    }
}