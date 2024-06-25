package com.dailyscoop.app.repository

import app.cash.turbine.test
import com.dailyscoop.app.data.repositories.NewsRepository
import com.dailyscoop.app.fake.FakeDataSource
import com.dailyscoop.app.fake.local.FakeNewsLocalDataSource
import com.dailyscoop.app.fake.network.FakeNewsNetworkDataSource
import com.dailyscoop.app.utilities.Result
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class NewsRepositoryTest {
    private val testDispatcher = UnconfinedTestDispatcher()

    private val testScope = TestScope(testDispatcher)

    private lateinit var networkDataSource: FakeNewsNetworkDataSource

    private lateinit var localDataSource: FakeNewsLocalDataSource

    private lateinit var newsRepository: NewsRepository

    @BeforeEach
    fun setup() {
        networkDataSource = FakeNewsNetworkDataSource()
        localDataSource = FakeNewsLocalDataSource()
        newsRepository =
            NewsRepository(
                networkDataSource = networkDataSource,
                localDataSource = localDataSource,
                ioDispatcher = testDispatcher,
            )
    }

    @Test
    fun `getLatestHeadlines should retrieve latest news headlines from remote api when no existing cached headlines`() =
        testScope.runTest {
            newsRepository.getLatestHeadlines().test {
                val latestHeadlines = awaitItem() as Result.Success
                assertThat(latestHeadlines.data.first()).isEqualTo(FakeDataSource.localHeadline1)
                awaitComplete()
            }
        }

    @Test
    fun `getLatestHeadlines should retrieve expected news headlines from local db when there are existing ones`() =
        testScope.runTest {
            localDataSource.insertArticles(FakeDataSource.remoteNewsArticles)

            newsRepository.getLatestHeadlines().test {
                val cachedHeadlines = awaitItem() as Result.Success

                assertThat(cachedHeadlines.data.size).isEqualTo(2)
                assertThat(cachedHeadlines.data).isEqualTo(FakeDataSource.localNewsHeadlines)

                awaitComplete()
            }
        }

    @Test
    fun `getArticleInfo should retrieve specific news article from local db when there are cached news headlines`() =
        testScope.runTest {
            localDataSource.insertArticles(FakeDataSource.remoteNewsArticles)

            newsRepository.getArticleInfo(
                newsId = 2,
                externalId = "57fe599411e31393e29111b6510c8460",
            ).test {
                val newsArticle = awaitItem()

                assertThat(newsArticle).isEqualTo(FakeDataSource.localNewsArticle2)
                assertThat(cancelAndConsumeRemainingEvents().isEmpty()).isTrue()
            }
        }
}
