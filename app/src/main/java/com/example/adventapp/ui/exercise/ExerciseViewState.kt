package com.example.adventapp.ui.exercise

import com.example.adventapp.domain.entity.Question
import com.example.common.mvvm.ViewState

internal data class ExerciseViewState(
    val currentQuestion: Question? = Question.defaultQuestion
) : ViewState