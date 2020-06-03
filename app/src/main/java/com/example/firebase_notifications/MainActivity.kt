package com.example.firebase_notifications;

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {
    lateinit var spinner : Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        spinner = findViewById(R.id.spinnerTopics)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel =
                NotificationChannel("Channel_01", "Notification_Example", importance)
            channel.description = "Description"
            channel.enableLights(true)
            channel.lightColor = Color.CYAN
            channel.enableVibration(true)
            notificationManager.createNotificationChannel(channel)
        }
        findViewById<View>(R.id.buttonSubscribe).setOnClickListener {
            val topic = spinner.selectedItem.toString()
            FirebaseMessaging.getInstance().subscribeToTopic(topic)
            Toast.makeText(applicationContext, "Topic Subscribed", Toast.LENGTH_LONG)
                .show()
        }
    }
}