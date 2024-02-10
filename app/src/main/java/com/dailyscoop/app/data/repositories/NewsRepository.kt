package com.dailyscoop.app.data.repositories

import com.dailyscoop.app.data.network.onError
import com.dailyscoop.app.data.network.onException
import com.dailyscoop.app.data.network.onSuccess
import com.dailyscoop.app.data.source.local.INewsLocalDataSource
import com.dailyscoop.app.data.source.remote.INewsNetworkDataSource
import com.dailyscoop.app.di.Dispatcher
import com.dailyscoop.app.di.NewsDispatchers
import com.dailyscoop.app.model.Article
import com.dailyscoop.app.utilities.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

/**
 * Concrete implementation for utilizing news data by communicating to api and accessing local cache
 */
class NewsRepository
    @Inject
    constructor(
        private val networkDataSource: INewsNetworkDataSource,
        private val localDataSource: INewsLocalDataSource,
        @Dispatcher(NewsDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    ) : INewsRepository {
        override fun getLatestHeadlines() =
            flow {
                val cachedHeadlines = localDataSource.getHeadlines()
                if (cachedHeadlines.isEmpty()) {
                    networkDataSource.getLatestHeadlines(
                        timePeriod = "1d",
                        language = "en",
                        country = "PH",
                        topic = "news",
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

        override fun getArticleInfo(
            newsId: Int,
            externalId: String,
        ): Flow<Article> = localDataSource.getArticleInfo(newsId = newsId, externalId = externalId)
    }
