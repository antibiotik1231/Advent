package com.example.adventapp.ui.container

import com.example.adventapp.Screens
import com.example.adventapp.domain.interactor.GiphyInteractor
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
    @Assisted backgroundImageId: Long,
    @Assisted description: String,
    @Assisted position: Int,
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

    fun onTryAgainButtonPressed(backgroundImageId: Long, position: Int) {
        router.backTo(Screens.ExerciseScreen(backgroundImageId, position))
    }

    fun onBackButtonPressed(backgroundImageId: Long) {
        router.backTo(Screens.MainScreen(backgroundImageId))
    }

    fun onBackPressed() {
        router.exit()
    }

    @AssistedFactory
    interface Factory {
        fun get(backgroundImageId: Long, description: String, position: Int): ContainerViewModel
    }
}
