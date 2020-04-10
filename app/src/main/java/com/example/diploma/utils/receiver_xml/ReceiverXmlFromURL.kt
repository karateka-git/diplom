package com.example.diploma.utils.receiver_xml

import com.example.diploma.utils.CustomException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.io.InputStream
import java.net.MalformedURLException
import java.net.URL

class ReceiverXmlFromURL : IReceiverXml {
    private val TAG = javaClass.name
    private val path = "http://192.168.0.101/schedule.xml"

    override suspend fun getInputStream(): InputStream {
        try {
            return withContext(Dispatchers.IO) {
                val url = URL(path)
                url.openConnection().getInputStream()
            }
        } catch (e: MalformedURLException) {
            throw CustomException("$TAG, Invalid URL. ${e.message}")
        } catch (e: IOException) {
            throw CustomException("$TAG, IO Exception reading data: ${e.message}")
        } catch (e: SecurityException) {
            throw CustomException("$TAG, Security Exception.  Needs permission? ${e.message}")
        }

    }
}