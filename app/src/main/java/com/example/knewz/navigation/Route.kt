package com.example.knewz.navigation

object Route {
    const val LOGIN = "login_main"
    const val SIGNUP = "signup"
    const val PROFILE = "profile"
    const val SEARCH_MAIN = "search/main"
    const val SEARCH_DETAIL = "search_detail/{query}/{fromKeyword}"

    // 유틸리티 함수
    fun searchDetail(query: String, fromKeyword: Boolean = false) =
        "search_detail/$query/$fromKeyword"
}