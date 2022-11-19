package com.example.adventapp.di

import com.example.adventapp.ui.MainActivity
import com.example.adventapp.ui.container.ContainerFragment
import com.example.adventapp.ui.dialog.HintDialogFragment
import com.example.adventapp.ui.exercise.ExerciseFragment
import com.example.adventapp.ui.landing.LandingFragment
import com.example.adventapp.ui.main.MainFragment
import com.example.adventapp.ui.menu.MenuFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NavigationModule::class,
        DatabaseModule::class,
        NetworkModule::class
    ]
)
internal interface AppComponent {
    fun inject(activity: MainActivity)

    fun inject(fragment: MainFragment)

    fun inject(fragment: MenuFragment)

    fun inject(fragment: ExerciseFragment)

    fun inject(fragment: ContainerFragment)

    fun inject(fragment: LandingFragment)
}