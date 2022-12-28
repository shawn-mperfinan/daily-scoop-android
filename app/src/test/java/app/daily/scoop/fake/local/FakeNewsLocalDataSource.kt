package app.daily.scoop.fake.local

import app.daily.scoop.data.database.model.ArticleEntity
import app.daily.scoop.data.database.model.asDomainModel
import app.daily.scoop.data.network.models.ArticleDto
import app.daily.scoop.data.source.local.INewsLocalDataSource
import app.daily.scoop.model.Article
import app.daily.scoop.model.Headline
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.mapLatest

/**
 * test double for [INewsLocalDataSource]
 */
@OptIn(ExperimentalCoroutinesApi::class)
class FakeNewsLocalDataSource : INewsLocalDataSource {

    private val articleEntitiesStateFlow = MutableStateFlow(listOf<ArticleEntity>())

    override suspend fun insertArticles(articles: List<ArticleDto>) {
        val transformedArticles = articles.mapIndexed { index, article ->
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
        articleEntitiesStateFlow.value = transformedArticles
    }

    override suspend fun getHeadlines(): List<Headline> = articleEntitiesStateFlow.first().map {
        Headline(
            id = it.id,
            title = it.title,
            topic = it.topic,
            publishedDate = it.publishedDate,
            mediaUrl = it.mediaUrl,
            externalId = it.externalId
        )
    }

    override fun getArticleInfo(newsId: Int, externalId: String): Flow<Article> =
        articleEntitiesStateFlow.mapLatest { articles ->
            articles.find { article -> article.id == newsId && article.externalId == externalId }!!.asDomainModel()
        }
}
