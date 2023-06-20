package com.dailyscoop.app.model

data class Article(
    val id: Int,
    val title: String,
    val author: String,
    val excerpt: String,
    val summary: String,
    val topic: String,
    val publishedDate: String,
    val originalNewsLink: String,
    val sourceLink: String,
    val mediaUrl: String,
    val externalId: String
)
