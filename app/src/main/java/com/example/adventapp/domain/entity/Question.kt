package com.example.adventapp.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class Question(
    val id: Int,
    val text: String,
    val hint: String,
    val answer: String
) : Parcelable {
    companion object {

        private const val DEFAULT_ID = 0

        val defaultQuestion = Question(DEFAULT_ID, "", "", "")
    }
}