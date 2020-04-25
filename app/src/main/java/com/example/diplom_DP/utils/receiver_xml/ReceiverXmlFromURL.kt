package com.example.diplom_DP.utils.receiver_xml

import android.content.Context
import com.example.diplom_DP.Constants
import com.example.diplom_DP.utils.exception.CustomException
import com.example.diplom_DP.utils.NetworkUtils
import com.example.diplom_DP.utils.exception.network.InternetConnectionException
import com.example.diplom_DP.utils.exception.network.ServerConnectionException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.io.InputStream
import java.net.MalformedURLException
import java.net.URL

class ReceiverXmlFromURL(private val context: Context) : IReceiverXml {
    private val TAG = javaClass.name
    private val path = Constants.xmlSchedulePath

    override suspend fun getInputStream(): InputStream {
        try {
            if (!NetworkUtils.hasInternetConnected(context))
                throw InternetConnectionException(
                    "$TAG, Internet connection exception"
                )
            if (!NetworkUtils.hasServerConnected(context))
                throw ServerConnectionException(
                    "$TAG, Server connection exception"
                )
            return withContext(Dispatchers.IO) {
                URL(path).openConnection().getInputStream()
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