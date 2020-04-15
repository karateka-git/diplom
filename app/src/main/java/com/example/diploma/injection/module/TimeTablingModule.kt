package com.example.diploma.injection.module

import android.content.Context
import com.example.diploma.repository.time_tabling.ITimeTablingRepository
import com.example.diploma.repository.time_tabling.XmlTimeTablingRepository
import com.example.diploma.utils.receiver_xml.IReceiverXml
import com.example.diploma.utils.receiver_xml.ReceiverXmlFromURL
import dagger.Module
import dagger.Provides

@Module
object TimeTablingModule {
    @Provides
    @JvmStatic
    fun provideTablingRepository(receiverXml: IReceiverXml): ITimeTablingRepository {
        return XmlTimeTablingRepository(receiverXml)
    }

    @Provides
    @JvmStatic
    fun provideReceiverXML(context: Context): IReceiverXml {
        return ReceiverXmlFromURL(context)
    }
}