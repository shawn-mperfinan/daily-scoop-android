package com.dailyscoop.app.data.source.local

import com.dailyscoop.app.data.database.dao.NewsArticleDao
import com.dailyscoop.app.data.database.model.ArticleEntity
import com.dailyscoop.app.data.database.model.asDomainModel
import com.dailyscoop.app.data.database.model.subset.HeadlineSubSet
import com.dailyscoop.app.data.database.model.subset.asDomainModel
import com.dailyscoop.app.data.network.models.ArticleDto
import com.dailyscoop.app.data.network.models.asEntityModel
import com.dailyscoop.app.model.Article
import com.dailyscoop.app.model.Headline
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Concrete implementation for utilizing news data by communicating to local room database
 */
class NewsLocalDataSource
    @Inject
    constructor(
        private val newsArticleDao: NewsArticleDao,
    ) : INewsLocalDataSource {
        override suspend fun insertArticles(articles: List<ArticleDto>) {
            newsArticleDao.insertArticles(articles.map(ArticleDto::asEntityModel))
        }

        override suspend fun getHeadlines(): List<Headline> =
            newsArticleDao.getHeadlines()
                .map(HeadlineSubSet::asDomainModel)

        override fun getArticleInfo(
            newsId: Int,
            externalId: String,
        ): Flow<Article> =
            newsArticleDao.getArticleInfo(newsId, externalId)
                .distinctUntilChanged()
                .map(ArticleEntity::asDomainModel)
    }
