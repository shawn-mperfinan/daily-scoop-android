package com.dailyscoop.app.model

data class Headline(
    val id: Int,
    val title: String,
    val topic: String,
    val publishedDate: String,
    val mediaUrl: String,
    val externalId: String,
)
