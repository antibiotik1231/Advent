package com.example.adventapp.di

import android.content.Context
import com.example.adventapp.AdventApp

internal fun Context.getAppComponent(): AppComponent {
    return (applicationContext as AdventApp).appComponent
}
