package com.dailyscoop.app.data.repositories

import com.dailyscoop.app.model.Article
import com.dailyscoop.app.model.Headline
import com.dailyscoop.app.utilities.Result
import kotlinx.coroutines.flow.Flow

interface INewsRepository {

    fun getLatestHeadlines(): Flow<Result<List<Headline>>>

    fun getArticleInfo(newsId: Int, externalId: String): Flow<Article>
}
