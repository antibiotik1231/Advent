package com.example.adventapp.di

import android.content.Context
import androidx.room.Room
import com.example.adventapp.data.dao.QuestionBobbyDao
import com.example.adventapp.data.dao.QuestionDao
import com.example.adventapp.data.dao.QuestionDianaDao
import com.example.adventapp.data.database.AppBobbyDatabase
import com.example.adventapp.data.database.AppDianaDatabase
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
    fun provideDianaDatabase(context: Context): AppDianaDatabase = Room.databaseBuilder(
        context,
        AppDianaDatabase::class.java,
        APP_DATABASE_NAME
    ).createFromAsset("database/diana/questions.db").build()

    @Singleton
    @Provides
    fun provideBobbyDatabase(context: Context): AppBobbyDatabase = Room.databaseBuilder(
        context,
        AppBobbyDatabase::class.java,
        APP_DATABASE_NAME
    ).createFromAsset("database/bobby/questions.db").build()

    @Singleton
    @Provides
    fun provideQuestionDianaDao(appDianaDatabase: AppDianaDatabase): QuestionDianaDao =
        appDianaDatabase.questionDao()

    @Singleton
    @Provides
    fun provideQuestionBobbyDao(appBobbyDatabase: AppBobbyDatabase): QuestionBobbyDao =
        appBobbyDatabase.questionDao()
}
