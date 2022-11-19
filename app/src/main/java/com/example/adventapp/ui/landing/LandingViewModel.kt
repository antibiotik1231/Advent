package com.example.adventapp.ui.landing

import androidx.lifecycle.ViewModel
import com.example.adventapp.Screens
import com.example.adventapp.domain.entity.Mode
import com.example.adventapp.ui.UiModel
import com.github.terrakok.cicerone.Router
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

internal class LandingViewModel @AssistedInject constructor(
    @Assisted val backgroundImageId: Int,
    private val router: Router
): ViewModel() {

    fun onButtonPressed(mode: Mode) {
        router.navigateTo(Screens.MainScreen(UiModel(backgroundImageId, mode)))
    }

    @AssistedFactory
    interface Factory {
        fun get(backgroundImageId: Int): LandingViewModel
    }
}