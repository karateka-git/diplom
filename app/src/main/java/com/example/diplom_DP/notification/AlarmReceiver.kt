package com.example.diplom_DP.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.diplom_DP.Constants
import com.example.diplom_DP.model.Record
import java.io.ByteArrayInputStream
import java.io.IOException
import java.io.ObjectInput
import java.io.ObjectInputStream


class AlarmReceiver : BroadcastReceiver() {

    private val TAG = AlarmReceiver::class.java.simpleName

    override fun onReceive(context: Context, intent: Intent) {
        Log.d(TAG, "onReceive() called with: context = [$context], intent = [$intent]")
        var test = intent.getIntExtra("test", 0)
        if (intent.action != null) {
            if (intent.action.equals(Constants.actionShowRecord, ignoreCase = true)) {
                val bis = ByteArrayInputStream(intent.getByteArrayExtra(Record::class.java.simpleName))
                var objInput: ObjectInput? = null
                var record: Record? = null
                try {
                    objInput = ObjectInputStream(bis)
                    record = objInput.readObject() as Record
                    NotificationHelper.createRecordTimeNotification(context, record)
                } catch (e: ClassNotFoundException) {
                    e.printStackTrace()
                } catch (e: IOException) {
                    e.printStackTrace()
                } finally {
                    try {
                        objInput?.close()
                    } catch (ex: IOException) {
                        ex.printStackTrace()
                    }
                }
            }
        }
    }
}