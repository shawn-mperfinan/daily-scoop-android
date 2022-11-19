package app.daily.scoop.data.network

import app.daily.scoop.data.network.models.NewsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("latest_headlines")
    suspend fun getLatestHeadlines(
        @Query("when") timePeriod: String,
        @Query("lang") language: String,
        @Query("countries") country: String,
        @Query("topic") topic: String?,
        @Query("page_size") pageSize: Int,
    ): ApiResult<NewsResponseDto>
}
