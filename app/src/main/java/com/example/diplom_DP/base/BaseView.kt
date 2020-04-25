package com.example.diplom_DP.base

import android.content.Context
import android.os.Bundle


/**
 * Base view any view must implement.
 */
interface BaseView {
    /**
     * Returns the context in which the application is running.
     * @return the context in which the application is running
     */
    fun getContext() : Context

    fun getBundle() : Bundle?
}