package com.dailyscoop.app.di

import com.dailyscoop.app.data.repositories.INewsRepository
import com.dailyscoop.app.data.repositories.NewsRepository
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
