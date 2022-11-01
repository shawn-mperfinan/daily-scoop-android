package app.daily.scoop

import app.daily.scoop.data.datasources.NewsNetworkDataSource
import app.daily.scoop.data.network.ApiResult
import app.daily.scoop.data.network.NewsService
import app.daily.scoop.data.network.models.NewsResponse
import app.daily.scoop.data.network.onError
import app.daily.scoop.data.network.onSuccess
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
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

    private suspend fun getNewsHeadlines(): ApiResult<NewsResponse> {
        return newsNetworkDataSource.getLatestHeadlines(
            timePeriod = "1d",
            language = "en",
            country = "PH",
            topic = "news"
        )
    }

    @Test
    fun test401ResponseForGetLatestNewsHeadlines() = runTest {
        enqueueResponse(fileName = "HeadlinesUnauthorizedOrBadRequestResponse.json", statusCode = 401)
        val response = getNewsHeadlines()

        response.onError { errorCode, errorMessage ->
            assertEquals(errorCode, 401)
            assertEquals(errorMessage, "Invalid api key: zHne345")
        }
    }

    @Test
    fun test400ResponseForGetLatestNewsHeadlines() = runTest {
        enqueueResponse(fileName = "HeadlinesUnauthorizedOrBadRequestResponse.json", statusCode = 400)
        val response = getNewsHeadlines()

        response.onError { errorCode, errorMessage ->
            assertEquals(errorCode, 400)
            assertEquals(errorMessage, "Invalid api key: zHne345")
        }
    }

    @Test
    fun test200ResponseForGetLatestNewsHeadlines() = runTest {
        enqueueResponse(fileName = "HeadlinesSuccessfulResponse.json", statusCode = 200)
        val response = getNewsHeadlines()

        response.onSuccess { newsResponse ->
            val newsFirstItem = newsResponse.articles.first()
            assert(newsResponse.articles.isNotEmpty())
            assertEquals(newsResponse.totalHits, 909)
            assertEquals(newsFirstItem.title, "F1: Max Verstappen wins US Grand Prix")
            assertEquals(newsFirstItem.author, "Reuters")
            assertEquals(newsFirstItem.country, "PH")
        }
    }
}
