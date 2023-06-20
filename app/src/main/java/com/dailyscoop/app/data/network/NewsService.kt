package com.dailyscoop.app.data.network

import com.dailyscoop.app.data.network.models.NewsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("latest_headlines")
    suspend fun getLatestHeadlines(
        @Query("when") timePeriod: String,
        @Query("lang") language: String,
        @Query("countries") country: String,
        @Query("topic") topic: String?,
        @Query("page_size") pageSize: Int
    ): ApiResult<NewsDto>
}
