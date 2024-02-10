package com.dailyscoop.app.data.network.adapter

import com.dailyscoop.app.data.network.ApiResult
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class NetworkResultCallAdapterFactory private constructor() : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit,
    ): CallAdapter<*, *>? {
        val callType = getParameterUpperBound(0, returnType as ParameterizedType)
        val resultType = getParameterUpperBound(0, callType as ParameterizedType)

        return when {
            getRawType(returnType) != Call::class.java -> null
            getRawType(callType) != ApiResult::class.java -> null
            else -> NetworkResultCallAdapter(resultType)
        }
    }

    companion object {
        fun create(): NetworkResultCallAdapterFactory = NetworkResultCallAdapterFactory()
    }
}
