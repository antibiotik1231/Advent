package com.example.adventapp.ui.menu

import androidx.lifecycle.ViewModel
import com.example.adventapp.Screens
import com.example.adventapp.domain.interactor.QuestionInteractor
import com.example.adventapp.ui.recycler.StickersModel
import com.github.terrakok.cicerone.Router
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

internal class MenuViewModel @AssistedInject constructor(
    @Assisted val backgroundImageId: Long,
    private val router: Router,
    private val questionInteractor: QuestionInteractor
) : ViewModel() {

    fun setItems(stickers: MutableList<StickersModel>): MutableList<StickersModel> {
        return questionInteractor.setItems(stickers)
    }

    fun onBackPressed() {
        router.exit()
    }

    fun onItemClicked(backgroundImageId: Long, position: Int) {
        router.navigateTo(Screens.ExerciseScreen(backgroundImageId, position))
    }

    @AssistedFactory
    interface Factory {
        fun get(backgroundImageId: Long): MenuViewModel
    }
}
