package com.example.diplom_DP.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.diplom_DP.Constants
import com.example.diplom_DP.R
import com.example.diplom_DP.model.Record
import com.example.diplom_DP.utils.DateAndTimeUtility
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.ObjectOutputStream


object AlarmScheduler {
    /**
     * Creates a [PendingIntent] for the Alarm using the [Record]
     *
     * @param context   current application context
     * @param record    record for the notification
     */
    private fun createPendingIntent(context: Context, record: Record): PendingIntent {
        // create the intent using a unique type
        val intent = Intent(context.applicationContext, AlarmReceiver::class.java).apply {
            putExtra("notificationId", 23)
            action = Constants.actionShowRecord
            type = record.uuid.toString()
        }

        val bos = ByteArrayOutputStream()
        val out: ObjectOutputStream?
        try {
            out = ObjectOutputStream(bos)
            out.writeObject(record)
            out.flush()
            val data: ByteArray = bos.toByteArray()
            intent.putExtra(Record::class.java.simpleName, data)
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                bos.close()
            } catch (ex: IOException) {
                ex.printStackTrace()
            }
        }

        val request = (0..10).random() // TODO change to static
        Log.d("request", "$request")
        return PendingIntent.getBroadcast(context.applicationContext, request, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    /**
     * Schedules a single alarm
     */
    private fun scheduleAlarm(record: Record, alarmIntent: PendingIntent, alarmMgr: AlarmManager) {
        val date = DateAndTimeUtility
        val datetimeToAlarm = date.getTimeForSchedule(record)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmMgr.setExact(AlarmManager.RTC_WAKEUP, datetimeToAlarm.timeInMillis, alarmIntent)
        } else {
            alarmMgr.set(AlarmManager.RTC_WAKEUP, datetimeToAlarm.timeInMillis, alarmIntent)
        }
    }

    fun scheduleAlarmForRecord(context: Context, record: Record) {
        // get the AlarmManager reference
        val alarmMgr = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        // get the PendingIntent for the alarm
        val alarmIntent = createPendingIntent(context, record)

        scheduleAlarm(record, alarmIntent, alarmMgr)
    }
}