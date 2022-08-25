package com.example.adventapp.data.dao

import androidx.room.*
import com.example.adventapp.data.model.QuestionData
import io.reactivex.Completable
import io.reactivex.Single

@Dao
internal interface QuestionDao {

    @Query("SELECT * FROM questions")
    fun getAllQuestions(): Single<List<QuestionData>>

    @Query("SELECT * FROM questions WHERE id=:id")
    fun getQuestionById(id: Int): Single<QuestionData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertQuestion(questionData: QuestionData): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllQuestions(questions: List<QuestionData>): Completable
}