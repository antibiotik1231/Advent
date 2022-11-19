package com.example.adventapp.ui.exercise

import com.example.adventapp.Screens
import com.example.adventapp.domain.entity.Question
import com.example.adventapp.domain.interactor.QuestionInteractor
import com.example.adventapp.ui.ShowHintDialogCommand
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

internal class ExerciseViewModel @AssistedInject constructor(
    @Assisted val uiModel: UiModel,
    @Assisted val position: Int,
    private val router: Router,
    private val questionInteractor: QuestionInteractor
) : BaseViewModel<ExerciseViewState>() {

    companion object {
        private const val CORRECT = "CORRECT"
        private const val INCORRECT = "INCORRECT"
    }

    private var currentQuestion: Question? = null

    init {
        questionInteractor
            .getQuestion(position, uiModel.mode)
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

    fun onSubmitButtonClicked(answer: String?) {
         val tag = if (answer?.lowercase() == currentQuestion?.answer?.lowercase()) {
            CORRECT
        } else {
           INCORRECT
        }
        router.navigateTo(Screens.ContainerScreen(uiModel, tag, position))
    }

    fun onHintButtonClicked() {
        _viewCommands.onNext(ShowHintDialogCommand(currentQuestion!!.hint))
    }

    fun onBackPressed() {
        router.backTo(Screens.MenuScreen(uiModel))
    }

    @AssistedFactory
    interface Factory {
        fun get(uiModel: UiModel, position: Int): ExerciseViewModel
    }
}
