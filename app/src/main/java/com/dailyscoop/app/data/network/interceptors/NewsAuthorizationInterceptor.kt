package com.dailyscoop.app.data.network.interceptors

import com.dailyscoop.app.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class NewsAuthorizationInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader(X_API_KEY, API_KEY)
            .build()

        return chain.proceed(request)
    }

    companion object {
        private const val X_API_KEY = "x-api-key"
        private const val API_KEY = com.dailyscoop.app.BuildConfig.API_KEY
    }
}
