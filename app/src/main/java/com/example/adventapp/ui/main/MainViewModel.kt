package com.example.adventapp.ui.main

import androidx.lifecycle.ViewModel
import com.example.adventapp.Screens
import com.example.adventapp.domain.entity.Description
import com.github.terrakok.cicerone.Router
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

internal class MainViewModel @AssistedInject constructor(
    @Assisted val backgroundImageId: Long,
    private val router: Router
): ViewModel() {

    fun onStartButtonClicked(backgroundImageId: Long) {
        router.navigateTo(Screens.MenuScreen(backgroundImageId))
    }

    fun onAboutButtonClicked(backgroundImageId: Long) {
        router.navigateTo(Screens.ContainerScreen(backgroundImageId, Description.ABOUT.name, 0))
    }

    fun onExitButtonClicked() {
        router.exit()
    }

    @AssistedFactory
    interface Factory {
        fun get(backgroundImageId: Long): MainViewModel
    }
}
