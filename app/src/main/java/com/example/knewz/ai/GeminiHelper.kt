package com.example.knewz.ai

import com.example.knewz.BuildConfig
import com.google.ai.client.generativeai.GenerativeModel

object GeminiHelper {
    val model by lazy {
        GenerativeModel(
            modelName = "gemini-2.5-flash",
            apiKey = BuildConfig.GEMINI_API_KEY
        )
    }

    suspend fun summarize(article: String): String {
        val prompt = """
            다음 뉴스 기사 내용을 최대 2줄로 요약해줘.
            - 핵심 사실 중심
            - 객관적 요약
            - 불필요한 분석 X
            - ~습니다 말투로

            기사:
            $article
        """.trimIndent()

        val result = model.generateContent(prompt)
        return result.text ?: "요약 실패"
    }
}