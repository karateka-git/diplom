package com.example.diploma.injection.module

import com.example.diploma.utils.adapters.RecordsAdapter
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