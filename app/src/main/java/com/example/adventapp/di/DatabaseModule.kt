package com.example.adventapp.di

import android.content.Context
import androidx.room.Room
import com.example.adventapp.data.dao.QuestionDao
import com.example.adventapp.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class DatabaseModule(val context: Context) {

    companion object {
        private const val APP_DATABASE_NAME = "questions"
    }

    @Singleton
    @Provides
    fun provideDatabase(context: Context): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        APP_DATABASE_NAME
    ).createFromAsset("database/questions.db").build()

    @Singleton
    @Provides
    fun provideQuestionDao(appDatabase: AppDatabase): QuestionDao = appDatabase.questionDao()
}
