package app.daily.scoop.data.repositories

import app.daily.scoop.Result
import app.daily.scoop.data.network.onError
import app.daily.scoop.data.network.onException
import app.daily.scoop.data.network.onSuccess
import app.daily.scoop.data.source.local.NewsLocalDataSource
import app.daily.scoop.data.source.remote.NewsNetworkDataSource
import app.daily.scoop.di.Dispatcher
import app.daily.scoop.di.NewsDispatchers
import app.daily.scoop.model.Article
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

/**
 * Concrete implementation for utilizing news data by communicating to api and accessing local cache
 */
class NewsRepositoryImpl @Inject constructor(
    private val networkDataSource: NewsNetworkDataSource,
    private val localDataSource: NewsLocalDataSource,
    @Dispatcher(NewsDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : NewsRepository {

    override fun getLatestHeadlines() = flow {
        val cachedHeadlines = localDataSource.getHeadlines()
        if (cachedHeadlines.isEmpty()) {
            networkDataSource.getLatestHeadlines(
                timePeriod = "1d",
                language = "en",
                country = "PH",
                topic = "news"
            ).onSuccess { response ->
                localDataSource.apply {
                    insertArticles(response.articles)
                    emit(Result.Success(getHeadlines()))
                }
            }.onError { code, message ->
                emit(Result.Error(code, message))
            }.onException { throwable ->
                emit(Result.Error(message = throwable.message ?: throwable.localizedMessage))
            }
        } else {
            emit(Result.Success(cachedHeadlines))
        }
    }.onStart { Result.Loading }.flowOn(ioDispatcher)

    override fun getArticleInfo(newsId: Int, externalId: String): Flow<Article> =
        localDataSource.getArticleInfo(newsId = newsId, externalId = externalId)
}
