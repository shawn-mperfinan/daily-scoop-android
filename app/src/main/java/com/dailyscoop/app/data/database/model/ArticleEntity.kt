package com.dailyscoop.app.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dailyscoop.app.model.Article

@Entity(tableName = "article")
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val author: String,
    val excerpt: String,
    val summary: String,
    val topic: String,
    @ColumnInfo(name = "published_date") val publishedDate: String,
    @ColumnInfo(name = "original_news_link") val originalNewsLink: String,
    @ColumnInfo(name = "source_link") val sourceLink: String,
    @ColumnInfo(name = "media_url") val mediaUrl: String,
    @ColumnInfo(name = "external_id") val externalId: String,
)

fun ArticleEntity.asDomainModel() =
    Article(
        id = id,
        title = title,
        author = author,
        excerpt = excerpt,
        summary = summary,
        topic = topic,
        publishedDate = publishedDate,
        originalNewsLink = originalNewsLink,
        sourceLink = sourceLink,
        mediaUrl = mediaUrl,
        externalId = externalId,
    )
