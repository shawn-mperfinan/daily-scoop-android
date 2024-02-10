package com.dailyscoop.app.data.source.remote

import com.dailyscoop.app.data.network.ApiResult
import com.dailyscoop.app.data.network.models.NewsDto

interface INewsNetworkDataSource {
    suspend fun getLatestHeadlines(
        timePeriod: String,
        language: String,
        country: String,
        topic: String?,
    ): ApiResult<NewsDto>
}
