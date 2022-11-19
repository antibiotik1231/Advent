package com.example.adventapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.adventapp.data.dao.QuestionDianaDao
import com.example.adventapp.data.model.QuestionData

private const val DATABASE_VERSION = 1

@Database(
    entities = [QuestionData::class],
    version = DATABASE_VERSION
)
internal abstract class AppDianaDatabase : RoomDatabase() {

    abstract fun questionDao(): QuestionDianaDao
}