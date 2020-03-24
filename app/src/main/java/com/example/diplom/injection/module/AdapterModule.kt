package com.example.diplom.injection.module

import android.content.Context
import com.example.diplom.ui.main.RecordsAdapter
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
@Suppress("unused")
object AdapterModule {
    @Provides
    @JvmStatic
    @Reusable
    internal fun provideNotesAdapter(context: Context): RecordsAdapter {
        return RecordsAdapter(context)
    }
}