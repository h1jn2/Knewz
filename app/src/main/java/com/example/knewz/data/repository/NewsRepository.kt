package com.example.knewz.data.repository

import android.util.Log
import com.example.knewz.data.remote.NewsApiService
import com.example.knewz.data.remote.NewsItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(
    private val apiService: NewsApiService,
) {
    private val clientId = "e3gMBUEjpc3fih0_5vMr"
    private val clientSecret = "vsZiRQhQ59"

    suspend fun getNews(query: String, display: Int = 10, start: Int = 1): Result<List<NewsItem>> {
        return try {
            val response = apiService.searchNews(
                query = query,
                display = display,
                start = start,
                clientId = clientId,
                clientSecret = clientSecret
            )
            Result.success(response.items)
        } catch (e: Exception) {
            Log.e("NaverNewsRepository", e.toString())
            Result.failure(e)
        }
    }
}