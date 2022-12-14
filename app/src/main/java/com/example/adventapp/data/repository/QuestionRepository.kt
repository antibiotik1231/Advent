package com.example.adventapp.data.repository

import com.example.adventapp.data.dao.QuestionBobbyDao
import com.example.adventapp.data.dao.QuestionDianaDao
import com.example.adventapp.data.mapper.QuestionMapper
import com.example.adventapp.domain.entity.Mode
import com.example.adventapp.domain.entity.Question
import com.example.adventapp.ui.recycler.StickersModel
import io.reactivex.Single
import javax.inject.Inject

internal class QuestionRepository @Inject constructor(
    private val questionDianaDao: QuestionDianaDao,
    private val questionBobbyDao: QuestionBobbyDao
) {


    fun getQuestion(id: Int, mode: Mode): Single<Question> {
        val dao = if (mode == Mode.DIANA) {
            questionDianaDao
        } else {
            questionBobbyDao
        }
        return dao.getQuestionById(id)
            .map { QuestionMapper.mapDataToDomain(it) }
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