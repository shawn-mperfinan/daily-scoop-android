package app.daily.scoop.fake.network

import app.daily.scoop.data.network.ApiResult
import app.daily.scoop.data.network.models.NewsDto
import app.daily.scoop.data.source.remote.NewsNetworkDataSource
import app.daily.scoop.fake.FakeDataSource
import com.google.gson.Gson

/**
 * test double for [NewsNetworkDataSource]
 */
class FakeNewsNetworkDataSourceImpl : NewsNetworkDataSource {

    private val newsData = Gson().fromJson(FakeDataSource.remoteNewsData, NewsDto::class.java)

    override suspend fun getLatestHeadlines(
        timePeriod: String,
        language: String,
        country: String,
        topic: String?
    ): ApiResult<NewsDto> = ApiResult.Success(newsData)
}
