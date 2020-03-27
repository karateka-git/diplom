package com.example.diplom.base

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.diplom.injection.component.ActivityInjector
import com.example.diplom.injection.component.DaggerActivityInjector
import com.example.diplom.injection.module.ContextModule
import com.example.diplom.injection.module.RecordsAdapterModule
import com.example.diplom.ui.main.MainActivity

abstract class BaseActivity<P : BasePresenter<BaseView>> : BaseView, AppCompatActivity() {
    protected lateinit var presenter: P

    private val injector: ActivityInjector = DaggerActivityInjector
        .builder()
        .baseView(this)
        .contextModule(ContextModule)
//        .recordsAdapterModule(RecordsAdapterModule)
        .build()

    init {
        inject()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = instantiatePresenter()
    }

    /**
     * Instantiates the presenter the Activity is based on.
     */
    protected abstract fun instantiatePresenter(): P

    override fun getContext(): Context {
        return this
    }

    private fun inject() {
        when (this) {
            is MainActivity -> injector.inject(this)
        }
    }
}