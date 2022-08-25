package com.example.adventapp.ui.menu

import androidx.lifecycle.ViewModel
import com.example.adventapp.Screens
import com.example.adventapp.ui.recycler.StickersModel
import com.github.terrakok.cicerone.Router
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

internal class MenuViewModel @AssistedInject constructor(
    @Assisted val number: Long,
    private val router: Router
) : ViewModel() {

    fun setItems(lst: MutableList<StickersModel>): MutableList<StickersModel> {
        for (i in 1..31) {
            when (i % 10) {
                1 -> {
                    if (i == 1 || i == 31) {
                        lst.add(StickersModel(i.toString() + "st", "Saving Point"))
                    } else {
                        lst.add(StickersModel(i.toString() + "st", ""))
                    }
                }
                2 -> {
                    lst.add(StickersModel(i.toString() + "nd", ""))
                }
                3 -> {
                    lst.add(StickersModel(i.toString() + "rd", ""))
                }
                else -> if (i == 8 || i == 18 || i == 25) {
                    lst.add(StickersModel(i.toString() + "th", "Saving Point"))
                } else {
                    lst.add(StickersModel(i.toString() + "th", ""))
                }
            }
        }
        lst.shuffle()
        return lst
    }

    fun onBackPressed() {
        router.exit()
    }

    fun onItemClicked(number: Long, position: Int) {
        router.navigateTo(Screens.ExerciseScreen(number, position))
    }

    fun backMusic() {

    }

    @AssistedFactory
    interface Factory {
        fun get(number: Long): MenuViewModel
    }
}