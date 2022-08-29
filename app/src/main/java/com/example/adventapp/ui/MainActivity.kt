package com.example.adventapp.ui

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.adventapp.R
import com.example.adventapp.Screens
import com.example.adventapp.di.getAppComponent
import com.example.adventapp.service.ForegroundSoundService
import com.example.common.config.Config
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import javax.inject.Inject
import kotlin.random.Random

internal class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var router: Router

    private val navigator = AppNavigator(this, R.id.main_fragment)

    private val backgroundImage = listOf(
        R.drawable.bg1,
        R.drawable.bg2,
        R.drawable.bg4,
        R.drawable.bg5,
        R.drawable.bg6,
        R.drawable.bg8,
        R.drawable.bg9
    )

    lateinit var myIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        getAppComponent().inject(this)
        supportActionBar?.hide()

        val backgroundImageId = backgroundImage[Random.nextInt(0, backgroundImage.size)].toLong()
        if (supportFragmentManager.fragments.isEmpty()) {
            router.newRootScreen(Screens.MainScreen(backgroundImageId))
        }
        createNotificationChannel()

        myIntent = Intent(this, ForegroundSoundService::class.java)
        val buttonPlay = findViewById<Button>(R.id.main_activity_button_play)
        buttonPlay.setOnClickListener {
            stopService(myIntent)
            startService(myIntent)
        }

        val buttonStop = findViewById<Button>(R.id.main_activity_button_stop)
        buttonStop.setOnClickListener {
            stopService(myIntent)
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
        startService(myIntent)
    }

    override fun onPause() {
        super.onPause()
        stopService(myIntent)
        navigatorHolder.removeNavigator()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                Config.CHANNEL_ID,
                "Channel ID",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            getSystemService(NotificationManager::class.java).createNotificationChannel(
                serviceChannel
            )
        }
    }
}
