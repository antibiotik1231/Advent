package com.example.adventapp

import com.example.adventapp.ui.container.ContainerFragment
import com.example.adventapp.ui.exercise.ExerciseFragment
import com.example.adventapp.ui.main.MainFragment
import com.example.adventapp.ui.menu.MenuFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

internal object Screens {

   fun MainScreen(number: Long) = FragmentScreen(clearContainer = true) { MainFragment.newInstance(number) }

   fun MenuScreen(number: Long) = FragmentScreen(clearContainer = true) { MenuFragment.newInstance(number) }

   fun ExerciseScreen(number: Long, position: Int) = FragmentScreen(clearContainer = true) {
      ExerciseFragment.newInstance(number, position)
   }

   fun ContainerScreen(number: Long, description: String, position: Int) =
      FragmentScreen(clearContainer = true) {
         ContainerFragment.newInstance(number, description, position)
      }
}