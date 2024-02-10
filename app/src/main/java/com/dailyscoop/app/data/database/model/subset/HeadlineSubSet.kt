package com.dailyscoop.app.data.database.model.subset

import androidx.room.ColumnInfo
import com.dailyscoop.app.model.Headline

data class HeadlineSubSet(
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "topic") val topic: String,
    @ColumnInfo(name = "published_date") val publishedDate: String,
    @ColumnInfo(name = "media_url") val mediaUrl: String,
    @ColumnInfo(name = "external_id") val externalId: String,
)

fun HeadlineSubSet.asDomainModel() =
    Headline(
        id = id,
        title = title,
        topic = topic,
        publishedDate = publishedDate,
        mediaUrl = mediaUrl,
        externalId = externalId,
    )
