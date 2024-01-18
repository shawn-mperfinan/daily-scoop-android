package com.dailyscoop.app.data.network.models

import com.google.gson.annotations.SerializedName

data class ErrorResponseDto(
    @SerializedName("status")
    val status: String,

    @SerializedName("error_code")
    val errorCodeDescription: String,

    @SerializedName("message")
    val errorMessage: String
)
