package com.example.adventapp.ui.container

import com.example.adventapp.Screens
import com.example.adventapp.data.repository.GiphyRepositoryImpl
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
    @Assisted number: Long,
    @Assisted description: String,
    @Assisted position: Int,
    private val router: Router,
    private val giphyRepositoryImpl: GiphyRepositoryImpl
) : BaseViewModel<ContainerViewState>() {

    init {
        giphyRepositoryImpl
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

    fun onTryAgainButtonPressed(number: Long, position: Int) {
        router.backTo(Screens.ExerciseScreen(number, position))
    }

    fun onBackButtonPressed(number: Long) {
        router.backTo(Screens.MainScreen(number))
    }

    fun onBackPressed() {
        router.exit()
    }

    @AssistedFactory
    interface Factory {
        fun get(number: Long, description: String, position: Int): ContainerViewModel
    }
}