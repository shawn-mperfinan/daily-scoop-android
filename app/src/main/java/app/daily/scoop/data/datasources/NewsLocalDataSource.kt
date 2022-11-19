package app.daily.scoop.data.datasources

import app.daily.scoop.data.database.dao.NewsArticleDao
import app.daily.scoop.data.database.model.ArticleEntity
import app.daily.scoop.data.database.model.asDomainModel
import app.daily.scoop.data.database.model.subset.HeadlineSubSet
import app.daily.scoop.data.database.model.subset.asDomainModel
import app.daily.scoop.model.Article
import app.daily.scoop.model.Headline
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsLocalDataSource @Inject constructor(
    private val newsArticleDao: NewsArticleDao
) {

    suspend fun insertArticles(articles: List<ArticleEntity>) {
        newsArticleDao.insertArticles(articles)
    }

    suspend fun getHeadlines(): List<Headline> =
        newsArticleDao.getHeadlines().map(HeadlineSubSet::asDomainModel)

    fun getArticleInfo(newsId: Int, externalId: String): Flow<Article> =
        newsArticleDao.getArticleInfo(newsId, externalId).distinctUntilChanged().map(ArticleEntity::asDomainModel)
}
