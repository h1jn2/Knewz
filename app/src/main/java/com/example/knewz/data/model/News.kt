package com.example.knewz.data.model

import java.sql.Timestamp

data class News(
    val newsId: String = "",
    val title: String = "", // 제목
    val content: String = "", // 내용 요약
    val url: String = "", // 원문 링크
    val source: String = "", // 언론사
    val category: String = "", // 카테고리
    val thumbnail: String? = null, // 썸네일 URL
    val publishedAt: Timestamp? = null, // 발행 시각
    val aiScore: Double = 0.0, // AI 추천 점수
    val viewCount: Int = 0, // 조회수
    val scrapCount: Int = 0 // 스크랩 수

)