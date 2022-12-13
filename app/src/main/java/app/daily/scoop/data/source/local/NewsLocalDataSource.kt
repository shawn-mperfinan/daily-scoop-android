package app.daily.scoop.data.source.local

import app.daily.scoop.data.database.dao.NewsArticleDao
import app.daily.scoop.data.database.model.ArticleEntity
import app.daily.scoop.data.database.model.asDomainModel
import app.daily.scoop.data.database.model.subset.HeadlineSubSet
import app.daily.scoop.data.database.model.subset.asDomainModel
import app.daily.scoop.data.network.models.ArticleDto
import app.daily.scoop.data.network.models.asEntityModel
import app.daily.scoop.model.Article
import app.daily.scoop.model.Headline
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Concrete implementation for utilizing news data by communicating to local room database
 */
class NewsLocalDataSource @Inject constructor(
    private val newsArticleDao: NewsArticleDao
) : INewsLocalDataSource {

    override suspend fun insertArticles(articles: List<ArticleDto>) {
        newsArticleDao.insertArticles(articles.map(ArticleDto::asEntityModel))
    }

    override suspend fun getHeadlines(): List<Headline> =
        newsArticleDao.getHeadlines()
            .map(HeadlineSubSet::asDomainModel)

    override fun getArticleInfo(newsId: Int, externalId: String): Flow<Article> =
        newsArticleDao.getArticleInfo(newsId, externalId)
            .distinctUntilChanged()
            .map(ArticleEntity::asDomainModel)
}
