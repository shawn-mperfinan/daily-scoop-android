@file:Suppress("TooGenericExceptionCaught")

package app.daily.scoop.data.network.adapter

import app.daily.scoop.data.network.ApiResult
import app.daily.scoop.data.network.models.ErrorResponseDto
import com.google.gson.Gson
import okhttp3.Request
import okhttp3.ResponseBody
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class NetworkResultCall<T : Any>(
    private val proxy: Call<T>
) : Call<ApiResult<T>> {

    override fun enqueue(callback: Callback<ApiResult<T>>) {
        proxy.enqueue(
            object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val networkResult = handleApi { response }
                    callback.onResponse(this@NetworkResultCall, Response.success(networkResult))
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    val networkResult = ApiResult.Exception<T>(t)
                    callback.onResponse(this@NetworkResultCall, Response.success(networkResult))
                }
            }
        )
    }

    private fun <T : Any> handleApi(
        execute: () -> Response<T>
    ): ApiResult<T> {
        return try {
            val response = execute()
            val responseBody = response.body()

            if (response.isSuccessful && responseBody != null) {
                ApiResult.Success(responseBody)
            } else {
                ApiResult.Error(code = response.code(), message = getErrorMessage(response.errorBody()))
            }
        } catch (exception: HttpException) {
            ApiResult.Error(code = exception.code(), message = exception.message())
        } catch (exception: Throwable) {
            ApiResult.Exception(exception)
        }
    }

    private fun getErrorMessage(errorBody: ResponseBody?): String {
        val errorResponse = Gson().fromJson(errorBody?.string(), ErrorResponseDto::class.java)
        return errorResponse.errorMessage
    }

    override fun cancel() {
        proxy.cancel()
    }

    override fun execute(): Response<ApiResult<T>> = throw NotImplementedError()

    override fun clone(): Call<ApiResult<T>> = NetworkResultCall(proxy.clone())

    override fun isExecuted(): Boolean = proxy.isExecuted

    override fun isCanceled(): Boolean = proxy.isCanceled

    override fun request(): Request = proxy.request()

    override fun timeout(): Timeout = proxy.timeout()
}
