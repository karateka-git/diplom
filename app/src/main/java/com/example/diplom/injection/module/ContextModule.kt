package com.example.diplom.injection.module

import android.app.Application
import android.content.Context
import com.example.diplom.MyApplication
import com.example.diplom.base.BaseView
import dagger.Module
import dagger.Provides

/**
 * Module which provides all required dependencies about Context
 */
@Module
@Suppress("unused")
object ContextModule {
    /**
     * Provides the Context
     * @param baseView the BaseView used to provides the context
     * @return the Context to be provided
     */
    @Provides
    @JvmStatic
    internal fun provideContext(baseView: BaseView): Context {
        return baseView.getContext()
    }

    /**
     * Provides the Application Context
     * @param context Context in which the application is running
     * @return the Application Context to be provided
     */
    @Provides
    @JvmStatic
    internal fun provideApplication(context: Context): MyApplication {
        return context.applicationContext as MyApplication
    }
}