package com.example.knewz.navigation

object Route {
    const val LOGIN = "login_main"
    const val SIGNUP = "signup"
    const val PROFILE = "profile"
    const val SEARCH_MAIN = "search/main"
    const val SEARCH_DETAIL = "search/{query}"

    // 유틸리티 함수
    fun searchDetail(query: String) = "search/$query"
}