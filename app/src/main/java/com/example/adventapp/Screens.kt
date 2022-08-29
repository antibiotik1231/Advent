package com.example.adventapp

import com.example.adventapp.ui.container.ContainerFragment
import com.example.adventapp.ui.exercise.ExerciseFragment
import com.example.adventapp.ui.main.MainFragment
import com.example.adventapp.ui.menu.MenuFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

internal object Screens {

    fun MainScreen(backgroundImageId: Long) =
        FragmentScreen(clearContainer = true) { MainFragment.newInstance(backgroundImageId) }

    fun MenuScreen(backgroundImageId: Long) =
        FragmentScreen(clearContainer = true) { MenuFragment.newInstance(backgroundImageId) }

    fun ExerciseScreen(backgroundImageId: Long, position: Int) =
        FragmentScreen(clearContainer = true) {
            ExerciseFragment.newInstance(backgroundImageId, position)
        }

    fun ContainerScreen(backgroundImageId: Long, description: String, position: Int) =
        FragmentScreen(clearContainer = true) {
            ContainerFragment.newInstance(backgroundImageId, description, position)
        }
}
