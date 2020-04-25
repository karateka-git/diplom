package com.example.diplom_DP.injection.module

import com.example.diplom_DP.utils.adapters.RecordsAdapter
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