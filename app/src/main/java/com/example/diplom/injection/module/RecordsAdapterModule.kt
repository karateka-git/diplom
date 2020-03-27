package com.example.diplom.injection.module

import android.content.Context
import com.example.diplom.utils.adapters.RecordsAdapter
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
@Suppress("unused")
object RecordsAdapterModule {
    @Provides
    @JvmStatic
    @Reusable
    internal fun provideRecordsAdapter(listener: RecordsAdapter.OnRecordListener): RecordsAdapter {
        return RecordsAdapter(listener)
    }
}