package com.example.knewz.util

import java.net.URI
import java.util.Locale

fun getSourceFromUrl(url: String): String {
    return try {
        val uri = URI(url)
        var host = uri.host ?: return "알 수 없음"

        host = host.removePrefix("www.").removePrefix("m.").removePrefix("news.")
        host = host.removePrefix("app.").removePrefix("mnews.")

        val rootDomain = host
        val mappedSource = MEDIA_MAPPING[rootDomain.lowercase(Locale.ROOT)]

        return mappedSource ?: rootDomain
    } catch (e: Exception) {
        "알 수 없음"
    }
}