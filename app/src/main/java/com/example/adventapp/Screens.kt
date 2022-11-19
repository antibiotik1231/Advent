package com.example.adventapp

import com.example.adventapp.ui.UiModel
import com.example.adventapp.ui.container.ContainerFragment
import com.example.adventapp.ui.exercise.ExerciseFragment
import com.example.adventapp.ui.landing.LandingFragment
import com.example.adventapp.ui.main.MainFragment
import com.example.adventapp.ui.menu.MenuFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

internal object Screens {

    fun MainScreen(uiModel: UiModel) =
        FragmentScreen(clearContainer = true) { MainFragment.newInstance(uiModel) }

    fun MenuScreen(uiModel: UiModel) =
        FragmentScreen(clearContainer = true) { MenuFragment.newInstance(uiModel) }

    fun ExerciseScreen(uiModel: UiModel, position: Int) =
        FragmentScreen(clearContainer = true) {
            ExerciseFragment.newInstance(uiModel, position)
        }

    fun ContainerScreen(uiModel: UiModel, description: String, position: Int) =
        FragmentScreen(clearContainer = true) {
            ContainerFragment.newInstance(uiModel, description, position)
        }

    fun LandingScreen(backgroundImageId: Int) =
        FragmentScreen(clearContainer = true) { LandingFragment.newInstance(backgroundImageId) }
}
