package app.daily.scoop.data.network.models

import com.google.gson.annotations.SerializedName

data class Article(

    @SerializedName("_id")
    val id: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("author")
    val author: String,

    @SerializedName("published_date")
    val publishedDate: String,

    @SerializedName("published_date_precision")
    val publishedDatePrecision: String,

    @SerializedName("link")
    val link: String,

    @SerializedName("clean_url")
    val cleanUrl: String,

    @SerializedName("excerpt")
    val excerpt: String,

    @SerializedName("summary")
    val summary: String,

    @SerializedName("rights")
    val rights: String,

    @SerializedName("rank")
    val rank: Int,

    @SerializedName("topic")
    val topic: String,

    @SerializedName("country")
    val country: String,

    @SerializedName("language")
    val language: String,

    @SerializedName("authors")
    val authors: String,

    @SerializedName("media")
    val media: String,

    @SerializedName("is_opinion")
    val isOpinion: Boolean,

    @SerializedName("twitter_account")
    val twitterAccount: String,

    @SerializedName("_score")
    val score: Double?
)
