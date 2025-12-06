package com.example.knewz.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

fun getRelativeTime(publishedAt: Date?): String {
    if (publishedAt == null) {
        return ""
    }

    val now = Date()
    val diffMillis = now.time - publishedAt.time

    val diffMinutes = TimeUnit.MILLISECONDS.toMinutes(diffMillis)
    val diffHours = TimeUnit.MILLISECONDS.toHours(diffMillis)
    val diffDays = TimeUnit.MILLISECONDS.toDays(diffMillis)

    return when {
        diffMillis < 0 -> {
            val futureFormatter = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
            futureFormatter.format(publishedAt) // 정확한 날짜 표시
        }
        diffMinutes < 60 -> {
            if (diffMinutes <= 0) "방금 전" else "${diffMinutes}분 전"
        }
        diffHours < 24 -> {
            "${diffHours}시간 전"
        }
        diffDays < 7 -> {
            "${diffDays}일 전"
        }
        else -> {
            val exactDateFormatter = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
            exactDateFormatter.format(publishedAt)
        }
    }
}