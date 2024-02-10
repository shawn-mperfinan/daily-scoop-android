package com.dailyscoop.app.di

import com.dailyscoop.app.data.source.local.INewsLocalDataSource
import com.dailyscoop.app.data.source.local.NewsLocalDataSource
import com.dailyscoop.app.data.source.remote.INewsNetworkDataSource
import com.dailyscoop.app.data.source.remote.NewsNetworkDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {
    @Binds
    fun bindNewsNetworkDataSource(newsNetworkDataSource: NewsNetworkDataSource): INewsNetworkDataSource

    @Binds
    fun bindNewsLocalDataSource(newsLocalDataSource: NewsLocalDataSource): INewsLocalDataSource
}
