package com.dailyscoop.app.repository

import app.cash.turbine.test
import com.dailyscoop.app.data.repositories.NewsRepository
import com.dailyscoop.app.fake.FakeDataSource
import com.dailyscoop.app.fake.local.FakeNewsLocalDataSource
import com.dailyscoop.app.fake.network.FakeNewsNetworkDataSource
import com.dailyscoop.app.utilities.Result
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class NewsRepositoryImplTest {

    private lateinit var networkDataSource: FakeNewsNetworkDataSource

    private lateinit var localDataSource: FakeNewsLocalDataSource

    private lateinit var testDispatcher: TestDispatcher

    private lateinit var newsRepository: NewsRepository

    @BeforeEach
    fun createNewsRepository() {
        networkDataSource = FakeNewsNetworkDataSource()
        localDataSource = FakeNewsLocalDataSource()
        testDispatcher = StandardTestDispatcher()
        newsRepository = NewsRepository(
            networkDataSource = networkDataSource,
            localDataSource = localDataSource,
            ioDispatcher = testDispatcher
        )
    }

    @Test
    fun getLatestHeadlines_fetch_data_from_network() = runTest(testDispatcher) {
        newsRepository.getLatestHeadlines().test {
            val latestHeadlines = awaitItem() as Result.Success
            assertThat(latestHeadlines.data.first()).isEqualTo(FakeDataSource.localHeadline1)
            awaitComplete()
        }
    }

    @Test
    fun getLatestHeadlines_fetch_data_from_local_db() = runTest(testDispatcher) {
        localDataSource.insertArticles(FakeDataSource.remoteNewsArticles)
        newsRepository.getLatestHeadlines().test {
            val latestHeadlines = awaitItem() as Result.Success
            assertThat(latestHeadlines.data.size).isEqualTo(2)
            assertThat(latestHeadlines.data.first()).isEqualTo(FakeDataSource.localHeadline1)
            assertThat(latestHeadlines.data[1]).isEqualTo(FakeDataSource.localHeadline2)
            awaitComplete()
        }
    }

    @Test
    fun getArticleInfo_fetch_article_from_local_db() = runTest {
        localDataSource.insertArticles(FakeDataSource.remoteNewsArticles)
        newsRepository.getArticleInfo(newsId = 2, externalId = "57fe599411e31393e29111b6510c8460").test {
            val newsArticle = awaitItem()
            assertThat(newsArticle).isEqualTo(FakeDataSource.localNewsArticle2)
            assertThat(cancelAndConsumeRemainingEvents().isEmpty()).isTrue()
        }
    }
}
