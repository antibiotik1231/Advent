package com.example.adventapp.domain.repository

import com.example.adventapp.domain.entity.Question
import io.reactivex.Completable
import io.reactivex.Single

internal interface QuestionRepository {

    fun getAllQuestions(): Single<List<Question>>

    fun getQuestion(id: Int): Single<Question>

    fun insertQuestion(question: Question): Completable

    fun insertAllQuestions(questions: List<Question>): Completable
}