package com.example.company.myapplication

import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notify.setOnClickListener {
            NotificationCompat.Builder(this, "1").apply {
                setSmallIcon(R.drawable.notification_icon_background)
                setContentTitle("Title")
                setContentText(editText.text.toString())
            }.build().also { (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).notify(1, it) }
        }
    }
}
