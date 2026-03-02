package com.example.knewz.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private val apiDateFormat = SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH)

fun convertStringToDate(pubDate: String): Date? {
    return try {
        apiDateFormat.parse(pubDate)
    } catch (e: Exception) {
        null
    }
}
fun formatTimestamp(timestamp: Long): String {
    val date = Date(timestamp)
    val sdf = SimpleDateFormat("yyyy.MM.dd", Locale.KOREA)
    return sdf.format(date)
}