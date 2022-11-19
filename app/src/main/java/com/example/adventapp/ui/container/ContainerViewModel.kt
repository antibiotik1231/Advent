package com.example.adventapp.ui.container

import com.example.adventapp.Screens
import com.example.adventapp.domain.interactor.GiphyInteractor
import com.example.adventapp.ui.UiModel
import com.example.common.extensions.onNext
import com.example.common.mvvm.BaseViewModel
import com.github.terrakok.cicerone.Router
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

internal class ContainerViewModel @AssistedInject constructor(
    @Assisted val uiModel: UiModel,
    @Assisted val description: String,
    @Assisted val position: Int,
    private val router: Router,
    private val giphyInteractor: GiphyInteractor
) : BaseViewModel<ContainerViewState>() {

    init {
        giphyInteractor
            .getGif(tag = description)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { image ->
                    _viewState.onNext(ContainerViewState(giphyUrl = image.url))
                },
                { error -> Timber.e(error) }
            ).safeSubscribe()
    }

    fun onTryAgainButtonPressed() {
        router.backTo(Screens.ExerciseScreen(uiModel, position))
    }

    fun onBackButtonPressed() {
        router.backTo(Screens.MainScreen(uiModel))
    }

    fun onBackPressed() {
        router.exit()
    }

    @AssistedFactory
    interface Factory {
        fun get(uiModel: UiModel, description: String, position: Int): ContainerViewModel
    }
}
