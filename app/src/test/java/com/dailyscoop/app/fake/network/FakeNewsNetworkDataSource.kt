package com.dailyscoop.app.fake.network

import com.dailyscoop.app.data.network.ApiResult
import com.dailyscoop.app.data.network.models.NewsDto
import com.dailyscoop.app.data.source.remote.INewsNetworkDataSource
import com.dailyscoop.app.fake.FakeDataSource
import com.google.gson.Gson

/**
 * test double for [INewsNetworkDataSource]
 */
class FakeNewsNetworkDataSource : INewsNetworkDataSource {

    private val newsData = Gson().fromJson(FakeDataSource.remoteNewsData, NewsDto::class.java)

    override suspend fun getLatestHeadlines(
        timePeriod: String,
        language: String,
        country: String,
        topic: String?
    ): ApiResult<NewsDto> = ApiResult.Success(newsData)
}
