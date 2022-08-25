package com.example.adventapp.ui.exercise

import com.example.adventapp.Screens
import com.example.adventapp.data.network.GiphyApi
import com.example.adventapp.data.repository.GiphyRepositoryImpl
import com.example.adventapp.data.repository.QuestionRepositoryImpl
import com.example.adventapp.domain.entity.Question
import com.example.adventapp.ui.ShowHintDialogCommand
import com.example.common.extensions.onNext
import com.example.common.mvvm.BaseViewModel
import com.github.terrakok.cicerone.Router
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

internal class ExerciseViewModel @AssistedInject constructor(
    @Assisted number: Long,
    @Assisted position: Int,
    private val router: Router,
    private val questionRepositoryImpl: QuestionRepositoryImpl,
    private val giphyRepositoryImpl: GiphyRepositoryImpl
) : BaseViewModel<ExerciseViewState>() {

    companion object {
        private const val CORRECT = "CORRECT"
        private const val INCORRECT = "INCORRECT"
    }

    private var disposable: Disposable? = null

    private var currentQuestion: Question? = null

    init {
        questionRepositoryImpl
            .getQuestion(position)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { question ->
                    _viewState.onNext(ExerciseViewState(currentQuestion = question))
                    currentQuestion = question
                },
                { error ->
                    Timber.e(error)
                }
            )
            .safeSubscribe()
    }

    fun onSubmitButtonClicked(number: Long, answer: String?, position: Int) {
         val tag = if (answer?.lowercase() == currentQuestion?.answer?.lowercase()) {
            CORRECT
        } else {
           INCORRECT
        }
        router.navigateTo(Screens.ContainerScreen(number, tag, position))
    }

    fun onHintButtonClicked() {
        _viewCommands.onNext(ShowHintDialogCommand(currentQuestion!!.hint))
    }

    fun onBackPressed(number: Long) {
        router.backTo(Screens.MenuScreen(number))
    }

    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }

    @AssistedFactory
    interface Factory {
        fun get(number: Long, position: Int): ExerciseViewModel
    }
}