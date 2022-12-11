package app.daily.scoop.data.source.local

import app.daily.scoop.data.network.models.ArticleDto
import app.daily.scoop.model.Article
import app.daily.scoop.model.Headline
import kotlinx.coroutines.flow.Flow

interface NewsLocalDataSource {

    suspend fun insertArticles(articles: List<ArticleDto>)

    suspend fun getHeadlines(): List<Headline>

    fun getArticleInfo(newsId: Int, externalId: String): Flow<Article>
}
