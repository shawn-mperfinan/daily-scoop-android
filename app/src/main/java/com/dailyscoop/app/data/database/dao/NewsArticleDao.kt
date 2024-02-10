package com.dailyscoop.app.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dailyscoop.app.data.database.model.ArticleEntity
import com.dailyscoop.app.data.database.model.subset.HeadlineSubSet
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<ArticleEntity>)

    @Query(
        value = """
            SELECT id, title, topic, published_date, media_url, external_id
            FROM article
        """,
    )
    suspend fun getHeadlines(): List<HeadlineSubSet>

    @Query(
        value = """
            SELECT * FROM article
            WHERE id = :newsId AND external_id = :externalId
        """,
    )
    fun getArticleInfo(
        newsId: Int,
        externalId: String,
    ): Flow<ArticleEntity>
}
