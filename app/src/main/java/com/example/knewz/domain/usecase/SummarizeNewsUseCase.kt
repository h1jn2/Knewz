package com.example.knewz.domain.usecase

import com.example.knewz.ai.GeminiHelper
import com.example.knewz.data.model.News
import javax.inject.Inject

class SummarizeNewsUseCase @Inject constructor(
    private val getArticleTextUseCase: GetArticleTextUseCase
) {
    suspend fun execute(news: News, onUpdateStatus: (String) -> Unit): String {
        onUpdateStatus("기사 본문 로드 및 요약 대기중...")

        val articleText = getArticleTextUseCase.execute(news.url)

        if (articleText.length < 50) {
            return "본문 텍스트가 너무 짧거나 유효하지 않습니다."
        }

        return try {
            GeminiHelper.summarize(articleText)
        } catch (e: Exception) {
            "요약 서버 오류: ${e.message}"
        }
    }
}