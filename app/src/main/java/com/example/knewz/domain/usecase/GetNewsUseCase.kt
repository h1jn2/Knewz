package com.example.knewz.domain.usecase

import com.example.knewz.data.remote.NewsItem
import com.example.knewz.data.repository.NewsRepository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(query: String = "주식", display: Int = 10, start: Int = 1): Result<List<NewsItem>> {
        return repository.getNews(query, display, start)
    }
}