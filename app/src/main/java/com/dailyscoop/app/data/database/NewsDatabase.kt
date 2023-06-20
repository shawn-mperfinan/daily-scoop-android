package com.dailyscoop.app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dailyscoop.app.data.database.dao.NewsArticleDao
import com.dailyscoop.app.data.database.model.ArticleEntity

@Database(
    entities = [ArticleEntity::class],
    version = 1,
    exportSchema = true
)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsArticleDao(): NewsArticleDao
}
