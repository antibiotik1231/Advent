package com.example.adventapp.ui.main

import androidx.lifecycle.ViewModel
import com.example.adventapp.Screens
import com.example.adventapp.domain.entity.Description
import com.github.terrakok.cicerone.Router
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import javax.inject.Inject

internal class MainViewModel @AssistedInject constructor(
    @Assisted val number: Long,
    val router: Router
): ViewModel() {

    fun onStartButtonClicked(number: Long) {
        router.navigateTo(Screens.MenuScreen(number))
    }

    fun onAboutButtonClicked(number: Long) {
        router.navigateTo(Screens.ContainerScreen(number, Description.ABOUT.name, 0))
    }

    fun onExitButtonClicked() {
        router.exit()
    }

    @AssistedFactory
    interface Factory {
        fun get(number: Long): MainViewModel
    }
}