package app.daily.scoop.di

import app.daily.scoop.data.repositories.NewsRepository
import app.daily.scoop.data.repositories.NewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindNewsRepository(
        newsRepository: NewsRepositoryImpl
    ): NewsRepository
}
