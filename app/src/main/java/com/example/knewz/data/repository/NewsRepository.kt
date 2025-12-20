package com.example.knewz.data.repository

import android.util.Log
import com.example.knewz.data.model.News
import com.example.knewz.data.remote.NewsApiService
import com.example.knewz.data.remote.NewsItem
import com.example.knewz.util.convertStringToDate
import com.example.knewz.util.getSourceFromUrl
import com.example.knewz.util.stripHtmlTags
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import javax.inject.Inject
import javax.inject.Singleton

private val NEWS_SELECTORS = listOf(
    "#dic_area",
    "#articeBody",
    "#articleBody",
    "#content",
    ".newsct_article",
    ".article_body",
    ".news_article",
    ".article-content",
    ".article_view",
    ".view_body",
    ".nd_area",
    ".entry-content",
    ".post-content",
    ".body_text",
    ".articleCont",
)

@Singleton
class NewsRepository @Inject constructor(
    private val apiService: NewsApiService,
) {
    private val clientId = "e3gMBUEjpc3fih0_5vMr"
    private val clientSecret = "vsZiRQhQ59"

    suspend fun getNews(query: String, display: Int = 50, start: Int = 1): Result<List<News>> {
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

    suspend fun fetchArticleText(url: String): String = withContext(Dispatchers.IO) {
        Log.d("ArticleFetcher", "--- Fetch Start: $url")

        return@withContext try {
            val doc = Jsoup.connect(url)
                .timeout(15000)
                .userAgent("Mozilla/5.0")
                .get()

            val candidates = NEWS_SELECTORS.mapNotNull { selector ->
                doc.selectFirst(selector)?.text()?.trim()?.let { Pair(selector, it) }
            }

            val best = candidates.maxByOrNull { it.second.length }?.second

            val cleaned = best
                ?.replace("무단 전재 및 재배포 금지", "")
                ?.replace("저작권자 ©", "")
                ?.replace("기자 =", "")
                ?.trim()

            if (!cleaned.isNullOrBlank()) {
                Log.d("ArticleFetcher", "--- Article Parsing SUCCESS")
                cleaned
            } else {
                val fallback = doc.body().text()
                if (fallback.length > 100) {
                    Log.w("ArticleFetcher", "--- Fallback extraction used")
                    fallback
                } else {
                    "본문을 찾을 수 없음"
                }
            }
        } catch (e: Exception) {
            Log.e("ArticleFetcher", "Parsing error: ${e.message}")
            "기사 로드 실패: ${e.message}"
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