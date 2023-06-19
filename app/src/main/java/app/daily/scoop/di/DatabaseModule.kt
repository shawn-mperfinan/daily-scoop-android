package app.daily.scoop.di

import android.content.Context
import androidx.room.Room
import app.daily.scoop.data.database.NewsDatabase
import app.daily.scoop.data.database.dao.NewsArticleDao
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
