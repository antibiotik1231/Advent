package com.example.adventapp.ui.menu

import androidx.lifecycle.ViewModel
import com.example.adventapp.Screens
import com.example.adventapp.domain.interactor.QuestionInteractor
import com.example.adventapp.ui.UiModel
import com.example.adventapp.ui.recycler.StickersModel
import com.github.terrakok.cicerone.Router
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

internal class MenuViewModel @AssistedInject constructor(
    @Assisted val uiModel: UiModel,
    private val router: Router,
    private val questionInteractor: QuestionInteractor
) : ViewModel() {

    fun setItems(stickers: MutableList<StickersModel>): MutableList<StickersModel> {
        return questionInteractor.setItems(stickers)
    }

    fun onBackPressed() {
        router.exit()
    }

    fun onItemClicked(position: Int) {
        router.navigateTo(Screens.ExerciseScreen(uiModel, position))
    }

    @AssistedFactory
    interface Factory {
        fun get(uiModel: UiModel): MenuViewModel
    }
}
