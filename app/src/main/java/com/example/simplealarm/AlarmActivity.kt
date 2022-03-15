package com.example.simplealarm

import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class AlarmActivity : AppCompatActivity() {
    var rington : Ringtone? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)

        var ringtonUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        if (ringtonUri == null){
            ringtonUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
        }
        rington = RingtoneManager.getRingtone(this,ringtonUri)
        rington?.play()
    }

    override fun onDestroy() {
        super.onDestroy()
        rington?.stop()
    }
}