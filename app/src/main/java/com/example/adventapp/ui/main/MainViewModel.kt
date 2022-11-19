package com.example.adventapp.ui.main

import androidx.lifecycle.ViewModel
import com.example.adventapp.Screens
import com.example.adventapp.domain.entity.Description
import com.example.adventapp.ui.UiModel
import com.github.terrakok.cicerone.Router
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

internal class MainViewModel @AssistedInject constructor(
    @Assisted val uiModel: UiModel,
    private val router: Router
) : ViewModel() {

    fun onStartButtonClicked() {
        router.navigateTo(Screens.MenuScreen(uiModel))
    }

    fun onAboutButtonClicked() {
        router.navigateTo(Screens.ContainerScreen(uiModel, "New Year is coming", 0))
    }

    fun onExitButtonClicked() {
        router.exit()
    }

    @AssistedFactory
    interface Factory {
        fun get(uiModel: UiModel): MainViewModel
    }
}
