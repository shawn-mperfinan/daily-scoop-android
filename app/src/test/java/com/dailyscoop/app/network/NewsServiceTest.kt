package com.dailyscoop.app.network

import com.dailyscoop.app.data.network.ApiResult
import com.dailyscoop.app.data.network.NewsService
import com.dailyscoop.app.data.network.models.NewsDto
import com.dailyscoop.app.data.network.onError
import com.dailyscoop.app.data.network.onSuccess
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class NewsServiceTest : PredefinedMockWebServer<NewsService>() {

    private lateinit var newsService: NewsService

    @BeforeEach
    fun createNewsService() {
        newsService = createService(NewsService::class.java)
    }

    private suspend fun getLatestHeadlines(): ApiResult<NewsDto> {
        return newsService.getLatestHeadlines(
            timePeriod = "1d",
            language = "en",
            country = "PH",
            topic = "news",
            pageSize = 50
        )
    }

    @Test
    fun getLatestHeadlines_invalid_key_response() = runTest {
        enqueueResponse(fileName = "HeadlinesUnauthorizedOrBadRequestResponse.json", statusCode = 401)
        val response = getLatestHeadlines()

        response.onError { errorCode, errorMessage ->
            assertThat(errorCode).isEqualTo(401)
            assertThat(errorMessage).isEqualTo("Invalid api key: zHne345")
        }
    }

    @Test
    fun getLatestHeadlines_limit_reached_response() = runTest {
        enqueueResponse(fileName = "HeadlinesLimitReachedResponse.json", statusCode = 401)
        val response = getLatestHeadlines()

        response.onError { errorCode, errorMessage ->
            assertThat(errorCode).isEqualTo(401)
            assertThat(errorMessage).isEqualTo("Monthly API calls limit reached: 50")
        }
    }

    @Test
    fun getLatestHeadlines_bad_request_response() = runTest {
        enqueueResponse(fileName = "HeadlinesUnauthorizedOrBadRequestResponse.json", statusCode = 400)
        val response = getLatestHeadlines()

        response.onError { errorCode, errorMessage ->
            assertThat(errorCode).isEqualTo(400)
            assertThat(errorMessage).isEqualTo("Invalid api key: zHne345")
        }
    }

    @Test
    fun getLatestHeadlines_successful_response() = runTest {
        enqueueResponse(fileName = "HeadlinesSuccessfulResponse.json", statusCode = 200)
        val response = getLatestHeadlines()

        response.onSuccess { newsResponse ->
            val newsFirstItem = newsResponse.articles.first()
            assertThat(newsResponse.articles.isNotEmpty()).isTrue()
            assertThat(newsResponse.totalHits).isEqualTo(909)
            assertThat(newsFirstItem.title).isEqualTo("F1: Max Verstappen wins US Grand Prix")
            assertThat(newsFirstItem.author).isEqualTo("Reuters")
            assertThat(newsFirstItem.country).isEqualTo("PH")
        }
    }
}
