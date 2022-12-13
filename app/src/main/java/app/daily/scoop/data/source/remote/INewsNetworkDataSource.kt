package app.daily.scoop.data.source.remote

import app.daily.scoop.data.network.ApiResult
import app.daily.scoop.data.network.models.NewsDto

interface INewsNetworkDataSource {

    suspend fun getLatestHeadlines(
        timePeriod: String,
        language: String,
        country: String,
        topic: String?
    ): ApiResult<NewsDto>
}
