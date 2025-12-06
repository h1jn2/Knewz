package com.example.knewz.util

import org.jsoup.Jsoup

fun stripHtmlTags(text: String): String {
    return Jsoup.parse(text).text()
}