package com.example.adventapp.data.mapper

import com.example.adventapp.data.model.QuestionData
import com.example.adventapp.domain.entity.Question

internal object QuestionMapper {

    fun mapDataToDomain(questionData: QuestionData): Question =
        Question(
            id = questionData.id,
            text = questionData.text ?: "",
            hint = questionData.hint ?: "",
            answer = questionData.answer ?: ""
        )

    fun mapDataToDomain(questions: List<QuestionData>): List<Question> =
        questions.map(::mapDataToDomain)

    fun mapDomainToData(question: Question): QuestionData =
        QuestionData(
            id = question.id,
            text = question.text,
            hint = question.hint,
            answer = question.answer
        )
}