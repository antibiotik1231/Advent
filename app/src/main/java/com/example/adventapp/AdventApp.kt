package com.example.adventapp

import android.app.Application
import com.example.adventapp.di.AppComponent
import com.example.adventapp.di.AppModule
import com.example.adventapp.di.DaggerAppComponent
import com.example.adventapp.di.DatabaseModule

internal class AdventApp : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .databaseModule(DatabaseModule(this))
            .build()
    }
}