package com.example.diplom_DP.utils

import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.example.diplom_DP.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import java.io.IOException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.URL

class NetworkUtils {
    companion object {
        private val classTag = NetworkUtils::class.java.simpleName
        private const val reachabilityServer = Constants.internetConnectionTestServer
        private const val myServer = Constants.xmlScheduleServer

        @JvmStatic
        fun hasNetworkAvailable(context: Context): Boolean {
            var result = false
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val networkCapabilities = connectivityManager.activeNetwork ?: return false
                val actNw =
                    connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
                result = when {
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            } else {
                connectivityManager.run {
                    connectivityManager.activeNetworkInfo?.run {
                        result = when (type) {
                            ConnectivityManager.TYPE_WIFI -> true
                            ConnectivityManager.TYPE_MOBILE -> true
                            ConnectivityManager.TYPE_ETHERNET -> true
                            else -> false
                        }

                    }
                }
            }
            return result
        }

        @JvmStatic
        fun hasInternetConnected(context: Context): Boolean {
            if (hasNetworkAvailable(context)) {
                try {
                    return runBlocking(Dispatchers.IO) {
                            val connection =
                                URL(reachabilityServer).openConnection() as HttpURLConnection
                            Log.d(classTag, "hasInternetConnected: ${connection.responseCode}")
                            connection.responseCode == HttpURLConnection.HTTP_OK
                                    || connection.responseCode == 429
                    }
                } catch (e: IOException) {
                    Log.e(classTag, "Error checking internet connection", e)
                }
            } else {
                Log.w(classTag, "No network available!")
            }
            Log.d(classTag, "hasInternetConnected: false")
            return false
        }

        @JvmStatic
        fun hasServerConnected(context: Context): Boolean {
            if (hasNetworkAvailable(context)) {
                try {
                    return runBlocking(Dispatchers.IO) {
                        val connection =
                            URL(myServer).openConnection() as HttpURLConnection
                        connection.connectTimeout = 2000
                        Log.d(classTag, "hasInternetConnected: ${connection.responseCode}")
                        connection.responseCode == HttpURLConnection.HTTP_OK
                                || connection.responseCode == 429
                    }
                } catch (e: SocketTimeoutException) {
                    Log.e(classTag, "Error server connection timeout")
                    return false
                } catch (e: IOException) {
                    Log.e(classTag, "Error checking server connection", e)
                }
            } else {
                Log.w(classTag, "Server is unavailable!")
            }
            Log.d(classTag, "hasServerConnected: false")
            return false
        }

        @JvmStatic
        fun showDialog(context: Context,
                       listener: IDialogListener, message: String) {
            val builder =
                AlertDialog.Builder(context)
            builder.setTitle(Constants.networkErrorTitle)
            builder.setMessage(message)
            builder.setCancelable(false)
            builder.setPositiveButton(Constants.buttonOk) { dialogInterface: DialogInterface, i: Int ->
                dialogInterface.dismiss()
                listener.onDialogPositiveClick()
            }
            val alertDialog = builder.create()
            alertDialog.show()
        }
    }

}