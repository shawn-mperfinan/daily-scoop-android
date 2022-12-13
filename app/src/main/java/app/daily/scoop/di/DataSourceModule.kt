package app.daily.scoop.di

import app.daily.scoop.data.source.local.INewsLocalDataSource
import app.daily.scoop.data.source.local.NewsLocalDataSource
import app.daily.scoop.data.source.remote.INewsNetworkDataSource
import app.daily.scoop.data.source.remote.NewsNetworkDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun bindNewsNetworkDataSource(
        newsNetworkDataSource: NewsNetworkDataSource
    ): INewsNetworkDataSource

    @Binds
    fun bindNewsLocalDataSource(
        newsLocalDataSource: NewsLocalDataSource
    ): INewsLocalDataSource
}
