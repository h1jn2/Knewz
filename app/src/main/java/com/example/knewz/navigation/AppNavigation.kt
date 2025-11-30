package com.example.knewz.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.knewz.ui.home.HomeScreen
import com.example.knewz.ui.keyword.KeywordScreen
import com.example.knewz.ui.mypage.MyPageScreen
import com.example.knewz.ui.notif.NotifScreen
import com.example.knewz.ui.scrap.ScrapScreen


@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Home.route,
        modifier = modifier
    ) {
        composable(BottomNavItem.Home.route) { HomeScreen() }
        composable(BottomNavItem.Keyword.route) { KeywordScreen() }
        composable(BottomNavItem.Scrap.route) { ScrapScreen() }
        composable(BottomNavItem.Notif.route) { NotifScreen() }
        composable(BottomNavItem.MyPage.route) { MyPageScreen() }
    }
}