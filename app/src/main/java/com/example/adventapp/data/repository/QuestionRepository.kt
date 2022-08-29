package com.example.adventapp.data.repository

import com.example.adventapp.data.dao.QuestionDao
import com.example.adventapp.data.mapper.QuestionMapper
import com.example.adventapp.domain.entity.Question
import com.example.adventapp.ui.recycler.StickersModel
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

internal class QuestionRepository @Inject constructor(
    private val questionDao: QuestionDao
) {

    fun getAllQuestions(): Single<List<Question>> {
        return questionDao.getAllQuestions()
            .map { questions ->
                QuestionMapper.mapDataToDomain(questions)
            }
    }

    fun getQuestion(id: Int): Single<Question> {
        return questionDao.getQuestionById(id)
            .map { QuestionMapper.mapDataToDomain(it) }
    }

    fun insertQuestion(question: Question): Completable {
        return questionDao.insertQuestion(
            QuestionMapper.mapDomainToData(question)
        )
    }

    fun insertAllQuestions(questions: List<Question>): Completable {
        return questionDao.insertAllQuestions(
            questions.map { QuestionMapper.mapDomainToData(it) }
        )
    }

    fun setStickers(lst: MutableList<StickersModel>): MutableList<StickersModel> {
        for (i in 1..31) {
            when (i % 10) {
                1 -> lst.add(StickersModel(i.toString() + "st"))
                2 -> lst.add(StickersModel(i.toString() + "nd"))
                3 -> lst.add(StickersModel(i.toString() + "rd"))
                else -> lst.add(StickersModel(i.toString() + "th"))
            }
        }
        lst.shuffle()
        return lst
    }
}