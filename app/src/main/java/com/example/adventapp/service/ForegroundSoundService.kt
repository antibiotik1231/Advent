package com.example.adventapp.service

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.adventapp.R
import com.example.adventapp.ui.MainActivity
import com.example.common.config.Config
import kotlin.random.Random

internal class ForegroundSoundService : Service() {

    private var player = MediaPlayer()

    private val playlist = listOf(
        R.raw.music1,
        R.raw.music2,
        R.raw.music3,
        R.raw.music4,
    )

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        player = MediaPlayer.create(this, playlist[Random.nextInt(0, playlist.size)])
        player.isLooping = true
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        player.start()
        val input = intent?.getStringExtra(Config.inputExtra)
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)
        val notification = NotificationCompat.Builder(this, Config.CHANNEL_ID)
            .setContentTitle(getString(R.string.notification_title))
            .setContentText(input)
            .setSmallIcon(R.mipmap.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .build()
        startForeground(1, notification)
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        player.stop()
        player.release()
        stopSelf()
        super.onDestroy()
    }
}