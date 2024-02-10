package com.dailyscoop.app.data.network

sealed class ApiResult<T : Any> {
    class Success<T : Any>(val data: T) : ApiResult<T>()

    class Error<T : Any>(val code: Int, val message: String?) : ApiResult<T>()

    class Exception<T : Any>(val exception: Throwable) : ApiResult<T>()
}

suspend fun <T : Any> ApiResult<T>.onSuccess(executable: suspend (T) -> Unit): ApiResult<T> =
    apply {
        if (this is ApiResult.Success<T>) {
            executable(data)
        }
    }

suspend fun <T : Any> ApiResult<T>.onError(executable: suspend (code: Int, message: String?) -> Unit): ApiResult<T> =
    apply {
        if (this is ApiResult.Error<T>) {
            executable(code, message)
        }
    }

suspend fun <T : Any> ApiResult<T>.onException(executable: suspend (exception: Throwable) -> Unit): ApiResult<T> =
    apply {
        if (this is ApiResult.Exception<T>) {
            executable(exception)
        }
    }
