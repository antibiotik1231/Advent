package com.example.adventapp.data.repository

import com.example.adventapp.data.dao.QuestionDao
import com.example.adventapp.data.mapper.QuestionMapper
import com.example.adventapp.domain.entity.Question
import com.example.adventapp.domain.repository.QuestionRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

internal class QuestionRepositoryImpl @Inject constructor(
    private val questionDao: QuestionDao
) : QuestionRepository {

    override fun getAllQuestions(): Single<List<Question>> {
        return questionDao.getAllQuestions()
            .map { questions ->
                QuestionMapper.mapDataToDomain(questions)
            }
    }

    override fun getQuestion(id: Int): Single<Question> {
        return questionDao.getQuestionById(id)
            .map { QuestionMapper.mapDataToDomain(it) }
    }

    override fun insertQuestion(question: Question): Completable {
        return questionDao.insertQuestion(
            QuestionMapper.mapDomainToData(question)
        )
    }

    override fun insertAllQuestions(questions: List<Question>): Completable {
        return questionDao.insertAllQuestions(
            questions.map { QuestionMapper.mapDomainToData(it) }
        )
    }
}