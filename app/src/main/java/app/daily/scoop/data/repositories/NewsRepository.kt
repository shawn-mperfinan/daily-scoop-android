package app.daily.scoop.data.repositories

import app.daily.scoop.Result
import app.daily.scoop.model.Article
import app.daily.scoop.model.Headline
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getLatestHeadlines(): Flow<Result<List<Headline>>>

    fun getArticleInfo(newsId: Int, externalId: String): Flow<Article>
}
