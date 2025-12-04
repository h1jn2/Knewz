package com.example.knewz.data.repository

import android.util.Log
import com.example.knewz.data.model.News
import com.example.knewz.data.remote.NewsApiService
import com.example.knewz.data.remote.NewsItem
import com.example.knewz.util.convertStringToDate
import com.example.knewz.util.getSourceFromUrl
import com.example.knewz.util.stripHtmlTags
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(
    private val apiService: NewsApiService,
) {
    private val clientId = "e3gMBUEjpc3fih0_5vMr"
    private val clientSecret = "vsZiRQhQ59"

    suspend fun getNews(query: String, display: Int = 3, start: Int = 1): Result<List<News>> {
        return try {
            val response = apiService.searchNews(
                query = query,
                display = display,
                start = start,
                clientId = clientId,
                clientSecret = clientSecret
            )
            val newsList = response.items.map { it.toNews() }

            Log.d("hjn", query)
            Result.success(newsList)

        } catch (e: Exception) {
            Log.e("NaverNewsRepository", e.toString())
            Result.failure(e)
        }
    }

    private fun NewsItem.toNews(): News {
        val processedTitle = stripHtmlTags(this.title)
        val processedContent = stripHtmlTags(this.description)
        val sourceName = getSourceFromUrl(this.originallink)
        val publishedDate = convertStringToDate(this.pubDate)

        return News(
            title = processedTitle,
            content = processedContent,
            url = this.originallink,
            source = sourceName,
            thumbnail = null,
            publishedAt = publishedDate
        )
    }
}