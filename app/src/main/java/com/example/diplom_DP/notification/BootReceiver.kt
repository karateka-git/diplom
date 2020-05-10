package com.example.diplom_DP.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.diplom_DP.injection.component.DaggerApplicationComponent

class BootReceiver : BroadcastReceiver() {
    val TAG = "Boot"
    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            Intent.ACTION_BOOT_COMPLETED -> {
                Log.d(TAG, "In receiver after boot")
                context.startService(Intent(context, BootService::class.java))
            }
        }
    }
}