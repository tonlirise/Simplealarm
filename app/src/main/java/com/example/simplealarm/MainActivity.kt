package com.example.simplealarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    val sdf = SimpleDateFormat("HH:ss", Locale.getDefault())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSetTime = findViewById<Button>(R.id.btnSetTime)
        btnSetTime.setOnClickListener {
            val timePicker = MaterialTimePicker.Builder()
                .setTitleText(getString(R.string.select_time_text))
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setHour(12)
                .setMinute(0)
                .build()
            timePicker.show(supportFragmentManager, PICKER_TAG)
            timePicker.addOnPositiveButtonClickListener{
                val calendar = Calendar.getInstance()
                calendar.set(Calendar.MILLISECOND, 0)
                calendar.set(Calendar.SECOND, 0)
                calendar.set(Calendar.MINUTE, timePicker.minute)
                calendar.set(Calendar.HOUR, timePicker.hour)

                val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
                val alarmClockInfo = AlarmManager.AlarmClockInfo(calendar.timeInMillis, null)
                alarmManager.setAlarmClock(alarmClockInfo,getAlarmPendingIntent(this))

                Toast.makeText(this,"Сигнал установлен на ${sdf.format(calendar.time)}",Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object {
        const val PICKER_TAG = "show_picker"
        const val REQ_CODE = 0

        fun getAlarmPendingIntent(context: Context) : PendingIntent{
            val alarmIntent = Intent(context, AlarmActivity::class.java)
            alarmIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            return PendingIntent.getActivity(context,REQ_CODE,alarmIntent,PendingIntent.FLAG_UPDATE_CURRENT)
        }
    }
}