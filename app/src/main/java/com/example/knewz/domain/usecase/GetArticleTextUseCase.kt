package com.example.knewz.domain.usecase

import com.example.knewz.data.repository.NewsRepository
import javax.inject.Inject

class GetArticleTextUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend fun execute(url: String): String {
        return repository.fetchArticleText(url)
    }
}