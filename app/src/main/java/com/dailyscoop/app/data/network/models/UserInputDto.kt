package com.dailyscoop.app.data.network.models

import com.google.gson.annotations.SerializedName

data class UserInputDto(

    @SerializedName("lang")
    val language: List<String>,

    @SerializedName("not_lang")
    val notLanguages: List<String>?,

    @SerializedName("countries")
    val countries: List<String>,

    @SerializedName("not_countries")
    val notCountries: List<String>?,

    @SerializedName("page")
    val page: Int,

    @SerializedName("size")
    val size: Int,

    @SerializedName("sources")
    val sources: List<String>?,

    @SerializedName("not_sources")
    val notSources: List<String>?,

    @SerializedName("topic")
    val topic: String?,

    @SerializedName("from")
    val from: String
)
