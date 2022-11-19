package com.example.adventapp.domain.interactor

import com.example.adventapp.data.repository.QuestionRepository
import com.example.adventapp.domain.entity.Mode
import com.example.adventapp.domain.entity.Question
import com.example.adventapp.ui.recycler.StickersModel
import io.reactivex.Single
import javax.inject.Inject

internal class QuestionInteractor @Inject constructor(
    private val questionRepository: QuestionRepository
) {

    fun getQuestion(id: Int, mode: Mode): Single<Question> {
        return questionRepository.getQuestion(id, mode)
    }

    fun setItems(stickers: MutableList<StickersModel>): MutableList<StickersModel> {
        return questionRepository.setStickers(stickers)
    }
}