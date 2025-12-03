package com.example.knewz.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

suspend fun getThumbnailUrl(originalUrl: String): String? = withContext(Dispatchers.IO) {
    return@withContext try {
        val doc: Document = Jsoup.connect(originalUrl)
            .timeout(8000)
            .userAgent("Mozilla/5.0")
            .get()

        val ogImage = doc.select("meta[property=og:image]").attr("content")
        if (ogImage.isNotEmpty()) return@withContext ogImage

        val twitterImage = doc.select("meta[name=twitter:image]").attr("content")
        if (twitterImage.isNotEmpty()) return@withContext twitterImage

        val schemaImage = doc.select("meta[itemprop=image]").attr("content")
        if (schemaImage.isNotEmpty()) return@withContext schemaImage

        null
    } catch (e: Exception) {
        null
    }
}