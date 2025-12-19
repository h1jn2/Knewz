package com.example.knewz.domain.usecase

import com.example.knewz.data.model.News
import com.example.knewz.util.getThumbnailUrl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class GetNewsWithThumbnailsUseCase @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
) {
    suspend fun execute(query: String): List<News> = coroutineScope {
        val result = getNewsUseCase.execute(query)
        val newsList = result.getOrDefault(emptyList())

        newsList.map { news ->
            async(Dispatchers.IO) {
                val img = getThumbnailUrl(news.url)
                if (img != null) news.copy(thumbnail = img) else news
            }
        }.awaitAll()
    }
}