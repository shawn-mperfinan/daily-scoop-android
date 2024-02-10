package com.dailyscoop.app

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import com.dailyscoop.app.data.database.NewsDatabase
import com.dailyscoop.app.data.database.dao.NewsArticleDao
import com.dailyscoop.app.data.database.model.subset.HeadlineSubSet
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.Matchers.hasSize
import org.hamcrest.collection.IsArrayContaining.hasItemInArray
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class NewsDatabaseTest {
    private lateinit var newsDatabase: NewsDatabase

    private lateinit var newsArticleDao: NewsArticleDao

    @BeforeEach
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        newsDatabase = Room.inMemoryDatabaseBuilder(context, NewsDatabase::class.java).build()
        newsArticleDao = newsDatabase.newsArticleDao()
    }

    @AfterEach
    fun tearDown() {
        newsDatabase.close()
    }

    @Test
    fun newsArticleDao_insert_articles() =
        runTest {
            newsArticleDao.insertArticles(com.dailyscoop.app.FakeDataSource.localNewsArticles)

            val newsHeadlineIds = newsArticleDao.getHeadlines().map(HeadlineSubSet::id)
            assertThat(newsHeadlineIds, equalTo(listOf(1, 2)))
        }

    @Test
    fun newsArticleDao_get_headlines() =
        runTest {
            newsArticleDao.insertArticles(com.dailyscoop.app.FakeDataSource.localNewsArticles)

            val newsHeadlines = newsArticleDao.getHeadlines()
            assertThat(newsHeadlines, hasSize(2))
            assertThat(newsHeadlines.toTypedArray(), hasItemInArray(com.dailyscoop.app.FakeDataSource.localHeadline2))
        }

    @Test
    fun newsArticleDao_get_article_info() =
        runTest {
            newsArticleDao.insertArticles(com.dailyscoop.app.FakeDataSource.localNewsArticles)

            val newsArticleInfo =
                newsArticleDao.getArticleInfo(
                    newsId = 1,
                    externalId = "2665f479d34776dc7220786ce0658b58",
                ).first()
            assertThat(newsArticleInfo, equalTo(com.dailyscoop.app.FakeDataSource.localNewsArticle1))
        }
}
