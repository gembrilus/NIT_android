package com.example.company.myapplication

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel()

        val intent = Intent(this, FinishActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        send_notification.setOnClickListener {
            NotificationCompat.Builder(this, "1")
                    .apply {
                        setSmallIcon(R.drawable.ic_launcher_background)
                        setContentTitle("Title")
                        setContentText("Sending a message to another activity")
                        setCategory(Notification.CATEGORY_EVENT)
                        setContentIntent(pendingIntent)
                        setAutoCancel(true)
                    }
                    .build()
                    .also {
                        (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).notify(1, it)
                    }
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val id = "1"
            val name = "channel-1"
            val descriptionText = "Channel"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(id, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

}
