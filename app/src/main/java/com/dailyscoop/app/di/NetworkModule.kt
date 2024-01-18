@file:Suppress("MagicNumber")

package com.dailyscoop.app.di

import com.dailyscoop.app.BuildConfig
import com.dailyscoop.app.data.network.NewsService
import com.dailyscoop.app.data.network.adapter.NetworkResultCallAdapterFactory
import com.dailyscoop.app.data.network.interceptors.NewsAuthorizationInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    private const val BASE_URL = "https://api.newscatcherapi.com/v2/"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(NewsAuthorizationInterceptor())

        return if (com.dailyscoop.app.BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            okHttpBuilder.addInterceptor(loggingInterceptor).build()
        } else {
            okHttpBuilder.build()
        }
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(NetworkResultCallAdapterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideDailyScoopService(
        retrofit: Retrofit
    ): NewsService = retrofit.create(NewsService::class.java)
}
