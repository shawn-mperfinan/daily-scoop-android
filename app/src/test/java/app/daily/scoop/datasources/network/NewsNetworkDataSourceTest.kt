package app.daily.scoop.datasources.network

import app.daily.scoop.data.datasources.NewsNetworkDataSource
import app.daily.scoop.data.network.ApiResult
import app.daily.scoop.data.network.NewsService
import app.daily.scoop.data.network.models.NewsResponseDto
import app.daily.scoop.data.network.onError
import app.daily.scoop.data.network.onSuccess
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class NewsNetworkDataSourceTest : PredefinedMockWebServer<NewsService>() {

    private lateinit var newsService: NewsService
    private lateinit var newsNetworkDataSource: NewsNetworkDataSource

    @BeforeEach
    fun init() {
        newsService = createService(NewsService::class.java)
        newsNetworkDataSource = NewsNetworkDataSource(newsService)
    }

    private suspend fun getNewsHeadlines(): ApiResult<NewsResponseDto> {
        return newsNetworkDataSource.getLatestHeadlines(
            timePeriod = "1d",
            language = "en",
            country = "PH",
            topic = "news"
        )
    }

    @Test
    fun getNewsHeadLines_invalid_key_response() = runTest {
        enqueueResponse(fileName = "HeadlinesUnauthorizedOrBadRequestResponse.json", statusCode = 401)
        val response = getNewsHeadlines()

        response.onError { errorCode, errorMessage ->
            assertEquals(401, errorCode)
            assertEquals("Invalid api key: zHne345", errorMessage)
        }
    }

    @Test
    fun getNewsHeadLines_limit_reached_response() = runTest {
        enqueueResponse(fileName = "HeadlinesLimitReachedResponse.json", statusCode = 401)
        val response = getNewsHeadlines()

        response.onError { errorCode, errorMessage ->
            assertEquals(401, errorCode)
            assertEquals("Monthly API calls limit reached: 50", errorMessage)
        }
    }

    @Test
    fun getNewsHeadLines_bad_request_response() = runTest {
        enqueueResponse(fileName = "HeadlinesUnauthorizedOrBadRequestResponse.json", statusCode = 400)
        val response = getNewsHeadlines()

        response.onError { errorCode, errorMessage ->
            assertEquals(400, errorCode)
            assertEquals("Invalid api key: zHne345", errorMessage)
        }
    }

    @Test
    fun getNewsHeadLines_successful_response() = runTest {
        enqueueResponse(fileName = "HeadlinesSuccessfulResponse.json", statusCode = 200)
        val response = getNewsHeadlines()

        response.onSuccess { newsResponse ->
            val newsFirstItem = newsResponse.articles.first()
            assertTrue(newsResponse.articles.isNotEmpty())
            assertEquals(909, newsResponse.totalHits)
            assertEquals("F1: Max Verstappen wins US Grand Prix", newsFirstItem.title)
            assertEquals("Reuters", newsFirstItem.author)
            assertEquals("PH", newsFirstItem.country)
        }
    }
}
