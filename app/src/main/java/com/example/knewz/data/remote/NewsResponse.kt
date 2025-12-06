package com.example.knewz.data.remote

data class NewsItem(
    val title: String,
    val originallink: String,
    val link: String,
    val description: String,
    val pubDate: String
)

data class NewsResponse(
    val items: List<NewsItem>
)