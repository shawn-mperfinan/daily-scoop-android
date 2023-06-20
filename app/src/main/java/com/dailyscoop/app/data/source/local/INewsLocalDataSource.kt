package com.dailyscoop.app.data.source.local

import com.dailyscoop.app.data.network.models.ArticleDto
import com.dailyscoop.app.model.Article
import com.dailyscoop.app.model.Headline
import kotlinx.coroutines.flow.Flow

interface INewsLocalDataSource {

    suspend fun insertArticles(articles: List<ArticleDto>)

    suspend fun getHeadlines(): List<Headline>

    fun getArticleInfo(newsId: Int, externalId: String): Flow<Article>
}
