package app.daily.scoop.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import app.daily.scoop.data.database.dao.NewsArticleDao
import app.daily.scoop.data.database.model.ArticleEntity

@Database(
    entities = [ArticleEntity::class],
    version = 1,
    exportSchema = true
)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsArticleDao(): NewsArticleDao
}
