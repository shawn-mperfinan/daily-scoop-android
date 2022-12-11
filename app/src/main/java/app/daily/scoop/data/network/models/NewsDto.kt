package app.daily.scoop.data.network.models

import com.google.gson.annotations.SerializedName

data class NewsDto(
    @SerializedName("status")
    val status: String,

    @SerializedName("total_hits")
    val totalHits: Int,

    @SerializedName("page")
    val page: Int,

    @SerializedName("total_pages")
    val totalPages: Int,

    @SerializedName("page_size")
    val pageSize: Int,

    @SerializedName("articles")
    val articles: List<ArticleDto>,

    @SerializedName("user_input")
    val userInput: UserInputDto
)
