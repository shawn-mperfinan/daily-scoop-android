package app.daily.scoop.di

import app.daily.scoop.data.repositories.INewsRepository
import app.daily.scoop.data.repositories.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindNewsRepository(
        newsRepository: NewsRepository
    ): INewsRepository
}
