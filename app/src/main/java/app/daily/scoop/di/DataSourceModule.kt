package app.daily.scoop.di

import app.daily.scoop.data.source.local.NewsLocalDataSource
import app.daily.scoop.data.source.local.NewsLocalDataSourceImpl
import app.daily.scoop.data.source.remote.NewsNetworkDataSource
import app.daily.scoop.data.source.remote.NewsNetworkDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun bindNewsNetworkDataSource(
        newsNetworkDataSource: NewsNetworkDataSourceImpl
    ): NewsNetworkDataSource

    @Binds
    fun bindNewsLocalDataSource(
        newsLocalDataSource: NewsLocalDataSourceImpl
    ): NewsLocalDataSource
}
