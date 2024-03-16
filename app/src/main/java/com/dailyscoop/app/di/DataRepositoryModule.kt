package com.dailyscoop.app.di

import com.dailyscoop.app.data.repositories.INewsRepository
import com.dailyscoop.app.data.repositories.IUserPreferencesRepository
import com.dailyscoop.app.data.repositories.NewsRepository
import com.dailyscoop.app.data.repositories.UserPreferencesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataRepositoryModule {
    @Binds
    fun bindNewsRepository(newsRepository: NewsRepository): INewsRepository

    @Binds
    fun bindUserPreferencesRepository(userPreferencesRepository: UserPreferencesRepository): IUserPreferencesRepository
}
