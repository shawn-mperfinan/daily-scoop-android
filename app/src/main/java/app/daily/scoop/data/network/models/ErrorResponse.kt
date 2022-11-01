package app.daily.scoop.data.network.models

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("status")
    val status: String,

    @SerializedName("error_code")
    val errorCodeDescription: String,

    @SerializedName("message")
    val errorMessage: String
)
