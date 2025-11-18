package com.example.knewz.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val label: String, val icon: ImageVector) {
    object Home : BottomNavItem("home", "홈", Icons.Outlined.Home)
    object Scrap : BottomNavItem("scrap", "스크랩", Icons.Outlined.Favorite)
    object Keyword : BottomNavItem("keyword", "키워드", Icons.Outlined.Create)
    object Notif : BottomNavItem("notif", "알림", Icons.Outlined.Email)
    object MyPage : BottomNavItem("mypage", "마이페이지", Icons.Outlined.Person)

    companion object {
        val items = listOf(Home, Scrap, Keyword, Notif, MyPage)
    }
}
