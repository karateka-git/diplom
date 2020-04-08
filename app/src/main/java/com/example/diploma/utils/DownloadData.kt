package com.example.diploma.utils

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.Reader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class DownloadData {
    private val TAG = this.javaClass.name
    private val path = "http://192.168.0.101/schedule.xml"
    suspend fun process(): String {
        val xmlResult = StringBuilder()
        try {
            withContext(Dispatchers.Default) {
                val url = URL(path)
                Log.e(TAG, "before connection")
                val connection = withContext(Dispatchers.IO) {
                    Log.e(TAG, "in connection - $url")
                    url.openConnection() as HttpURLConnection // TODO http -> https
                }
                Log.e(TAG, "after connection")
                BufferedReader(InputStreamReader(connection.inputStream) as Reader).use {
                    withContext(Dispatchers.IO) {
                        var line: String? = it.readLine()
                        while (line != null) {
                            Log.e(TAG, "$line\n")
                            xmlResult.append(line)
                            line = it.readLine()
                        }
                    }
                    xmlResult.toString()
                }
            }
        } catch (e: MalformedURLException) {
            Log.e(TAG, "downloadXML: Invalid URL " + e.message);
        } catch(e: IOException) {
            Log.e(TAG, "downloadXML: IO Exception reading data: " + e.message);
        } catch(e: SecurityException) {
            Log.e(TAG, "downloadXML: Security Exception.  Needs permisson? " + e.message);
        } finally {
            return xmlResult.toString()
        }
    }
}