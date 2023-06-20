package com.dailyscoop.app.di

import android.content.Context
import androidx.room.Room
import com.dailyscoop.app.data.database.NewsDatabase
import com.dailyscoop.app.data.database.dao.NewsArticleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideNewsDatabase(
        @ApplicationContext appContext: Context
    ): NewsDatabase = Room.databaseBuilder(
        appContext,
        NewsDatabase::class.java,
        "news-database"
    ).build()

    @Provides
    @Singleton
    fun provideNewsArticleDao(
        newsDatabase: NewsDatabase
    ): NewsArticleDao = newsDatabase.newsArticleDao()
}
