package com.example.adventapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
internal data class QuestionData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "text")
    val text: String?,

    @ColumnInfo(name = "hint")
    val hint: String?,

    @ColumnInfo(name = "answer")
    val answer: String?
)