package app.daily.scoop

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import app.daily.scoop.data.database.NewsDatabase
import app.daily.scoop.data.database.dao.NewsArticleDao
import app.daily.scoop.data.database.model.ArticleEntity
import app.daily.scoop.data.database.model.subset.HeadlineSubSet
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
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
    fun newsArticleDao_insert_articles() = runTest {
        val newsArticles = testArticleEntities()
        newsArticleDao.insertArticles(newsArticles)

        val fetchedHeadlines = newsArticleDao.getHeadlines()
        assertEquals(
            listOf(1, 2, 3),
            fetchedHeadlines.map(HeadlineSubSet::id)
        )
    }

    @Test
    fun newsArticleDao_get_headlines() = runTest {
        val newsArticles = testArticleEntities()
        newsArticleDao.insertArticles(newsArticles)

        val fetchedHeadlines = newsArticleDao.getHeadlines()
        assertEquals(
            listOf(1, 2, 3),
            fetchedHeadlines.map(HeadlineSubSet::id)
        )

        val headlineItem = testHeadlineItem()
        assertTrue(fetchedHeadlines.contains(headlineItem))
    }

    @Test
    fun newsArticleDao_get_article_info() = runTest {
        val newsArticles = testArticleEntities()
        newsArticleDao.insertArticles(newsArticles)

        val fetchedArticleInfo = newsArticleDao.getArticleInfo(
            newsId = 1,
            externalId = "externalId_1"
        ).first()
        assertEquals(
            testArticleEntities().first(),
            fetchedArticleInfo
        )
    }
}

private fun testArticleEntities(): List<ArticleEntity> = listOf(
    testArticleEntity(id = 1, title = "title_1"),
    testArticleEntity(id = 2, title = "title_2"),
    testArticleEntity(id = 3, title = "title_2")
)

private fun testArticleEntity(id: Int, title: String): ArticleEntity = ArticleEntity(
    id = id,
    title = title,
    author = "",
    excerpt = "",
    summary = "",
    topic = "",
    publishedDate = "",
    originalNewsLink = "",
    sourceLink = "",
    mediaUrl = "",
    externalId = "externalId_$id"
)

private fun testHeadlineItem(): HeadlineSubSet = HeadlineSubSet(
    id = 1,
    title = "title_1",
    topic = "",
    publishedDate = "",
    mediaUrl = "",
    externalId = "externalId_1"
)
