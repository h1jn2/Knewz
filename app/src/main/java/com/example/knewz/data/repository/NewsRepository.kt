package com.example.knewz.data.repository

import android.util.Log
import com.example.knewz.data.model.News
import com.example.knewz.data.remote.NewsApiService
import com.example.knewz.data.remote.NewsItem
import com.example.knewz.util.MEDIA_MAPPING
import com.example.knewz.util.getThumbnailUrl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import java.net.URI
import java.net.URLEncoder
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

private val PLACEHOLDER_IMAGE_URL = "https://blog.kakaocdn.net/dna/byIZZL/btsMyrQRWb9/AAAAAAAAAAAAAAAAAAAAAPG3vMG0hs5TXAjhk5fglaLksnKIs1thKShAX_CExXzA/img.png?credential=yqXZFxpELC7KVnFOS48ylbz2pIh7yKj8&expires=1767193199&allow_ip=&allow_referer=&signature=NRXa5vDF%2BTR1hLjOqxM4c2v4%2FZw%3D"

@Singleton
class NewsRepository @Inject constructor(
    private val apiService: NewsApiService,
) {
    private val clientId = "e3gMBUEjpc3fih0_5vMr"
    private val clientSecret = "vsZiRQhQ59"
    private val apiDateFormat = SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH)

    suspend fun getNews(query: String, display: Int = 3, start: Int = 1): Result<List<News>> {
        return try {
            val response = apiService.searchNews(
                query = URLEncoder.encode(query, "UTF-8"),
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
    private fun getSourceFromUrl(url: String): String {
        return try {
            val uri = URI(url)
            var host = uri.host ?: return "알 수 없음"

            host = host.removePrefix("www.").removePrefix("m.").removePrefix("news.")
            host = host.removePrefix("app.").removePrefix("mnews.")

            val rootDomain = host
            val mappedSource = MEDIA_MAPPING[rootDomain.lowercase(Locale.ROOT)]

            return mappedSource ?: rootDomain
        } catch (e: Exception) {
            "알 수 없음"
        }
    }

    private suspend fun NewsItem.toNews(): News {
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

    private fun stripHtmlTags(text: String): String {
        return Jsoup.parse(text).text()
    }
    private fun convertStringToDate(pubDate: String): Date? {
        return try {
            apiDateFormat.parse(pubDate)
        } catch (e: Exception) {
            null
        }
    }
}