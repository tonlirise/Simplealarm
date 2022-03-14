package com.example.simplealarm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSetTime = findViewById<Button>(R.id.btnSetTime)
        btnSetTime.setOnClickListener{
            val timePicker = MaterialTimePicker.Builder()
                .setTitleText(getString(R.string.select_time_text))
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setHour(12)
                .setMinute(0)
                .build()
            timePicker.show(supportFragmentManager, PICKER_TAG)
        }
    }

    companion object{
        const val PICKER_TAG = "show_picker"
    }
}