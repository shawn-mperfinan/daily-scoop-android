package com.dailyscoop.app.fake.repository

import com.dailyscoop.app.data.database.model.ArticleEntity
import com.dailyscoop.app.data.database.model.asDomainModel
import com.dailyscoop.app.data.repositories.INewsRepository
import com.dailyscoop.app.fake.FakeDataSource
import com.dailyscoop.app.model.Article
import com.dailyscoop.app.model.Headline
import com.dailyscoop.app.utilities.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.mapLatest

/**
 * test double for [INewsRepository]
 */
@OptIn(ExperimentalCoroutinesApi::class)
class FakeNewsRepository : INewsRepository {

    private val articleEntitiesStateFlow = MutableStateFlow(
        FakeDataSource.remoteNewsArticles.mapIndexed { index, article ->
            ArticleEntity(
                id = index + 1,
                title = article.title,
                author = article.author,
                excerpt = article.excerpt,
                summary = article.summary,
                topic = article.topic,
                publishedDate = article.publishedDate,
                originalNewsLink = article.link,
                sourceLink = article.cleanUrl,
                mediaUrl = article.mediaUrl,
                externalId = article.id
            )
        }
    )

    override fun getLatestHeadlines(): Flow<Result<List<Headline>>> = articleEntitiesStateFlow.mapLatest { articles ->
        val headlines = articles.map {
            Headline(
                id = it.id,
                title = it.title,
                topic = it.topic,
                publishedDate = it.publishedDate,
                mediaUrl = it.mediaUrl,
                externalId = it.externalId
            )
        }
        Result.Success(headlines)
    }

    override fun getArticleInfo(newsId: Int, externalId: String): Flow<Article> =
        articleEntitiesStateFlow.mapLatest { articles ->
            articles.find { it.id == newsId && it.externalId == externalId }!!.asDomainModel()
        }
}
