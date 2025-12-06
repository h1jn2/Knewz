package com.example.knewz.domain.usecase

import com.example.knewz.data.model.News
import com.example.knewz.data.repository.NewsRepository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend fun execute(query: String): Result<List<News>> {
        return repository.getNews(query)
    }
}