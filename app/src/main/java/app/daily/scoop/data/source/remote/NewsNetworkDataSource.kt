package app.daily.scoop.data.source.remote

import app.daily.scoop.data.network.ApiResult
import app.daily.scoop.data.network.NewsService
import app.daily.scoop.data.network.models.NewsDto
import javax.inject.Inject

/**
 * Concrete implementation for utilizing news data by communicating to news catcher api
 */
class NewsNetworkDataSource @Inject constructor(
    private val newsService: NewsService
) : INewsNetworkDataSource {

    /**
     * Get the latest news headlines given any time period, topic, country or language.
     * @param timePeriod - time period you want to get the latest headlines (e.g. 1d, 7d or 30d).
     * @param language - specifies the language of the headlines (e.g. en, tl, ja, ko, de or es).
     * @param country - country where the news publisher is located (e.g. US, PH, JP, KR, DE OR ES).
     * @param topic - (optional) topic to which you want to restrict the articles of your choice (e.g. sports, news).
     */
    override suspend fun getLatestHeadlines(
        timePeriod: String,
        language: String,
        country: String,
        topic: String?
    ): ApiResult<NewsDto> {
        return newsService.getLatestHeadlines(
            timePeriod = timePeriod,
            language = language,
            country = country,
            topic = topic,
            pageSize = PAGE_SIZE
        )
    }

    companion object {
        private const val PAGE_SIZE = 50
    }
}
