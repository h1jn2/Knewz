package com.example.knewz.ai

import android.util.Log
import com.example.knewz.BuildConfig
import com.google.ai.client.generativeai.GenerativeModel

object GeminiHelper {
    val model by lazy {
        val apiKey = BuildConfig.GEMINI_API_KEY // 값을 변수에 담고
        GenerativeModel(
            modelName = "gemini-2.5-flash",
            apiKey = BuildConfig.GEMINI_API_KEY
        )
    }

    suspend fun summarize(article: String): String {
        val prompt = """
            다음 뉴스 기사를 최대 3줄로 요약해줘.
            - 핵심 사실 중심
            - 기사 스타일 유지

            기사:
            $article
        """.trimIndent()

        val result = model.generateContent(prompt)
        return result.text ?: "요약 실패"
    }
}