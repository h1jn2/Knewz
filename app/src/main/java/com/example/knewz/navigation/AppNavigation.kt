package com.example.knewz.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.room.util.query
import com.example.knewz.ui.home.HomeScreen
import com.example.knewz.ui.keyword.KeywordScreen
import com.example.knewz.ui.login.LoginScreen
import com.example.knewz.ui.mypage.MyPageScreen
import com.example.knewz.ui.mypage.profile.ProfileScreen
import com.example.knewz.ui.notif.NotifScreen
import com.example.knewz.ui.scrap.ScrapScreen
import com.example.knewz.ui.search.SearchScreen
import com.example.knewz.ui.search_detail.SearchDetailScreen
import com.example.knewz.ui.signup.SignUpScreen


@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Home.route,
        modifier = modifier
    ) {
        val onSearchNavigate: (String) -> Unit = { query ->
            if (query.isNotBlank()) {
                navController.navigate(Route.searchDetail(query)) {
                    launchSingleTop = true
                }
            }
        }

        composable(BottomNavItem.Home.route) {
            HomeScreen(
                navController = navController,
                onNavigateToSearch = { navController.navigate(Route.SEARCH_MAIN) }
            )
        }
        composable(BottomNavItem.Keyword.route) { KeywordScreen(navController = navController) }
        composable(BottomNavItem.Scrap.route) { ScrapScreen(navController = navController) }
        composable(BottomNavItem.Notif.route) { NotifScreen() }

        composable(BottomNavItem.MyPage.route) {
            val auth = com.google.firebase.auth.FirebaseAuth.getInstance()
            val currentUser = auth.currentUser

            if (currentUser == null) {
                LoginScreen(
                    onNavigateToSignUp = { navController.navigate(Route.SIGNUP) },
                    onLoginSuccess = {
                        navController.navigate(BottomNavItem.MyPage.route) {
                            popUpTo(BottomNavItem.MyPage.route) { inclusive = true }
                        }
                    }
                )
            } else {
                MyPageScreen(
                    onNavigate = { route -> navController.navigate(route) }
                )
            }
        }

        composable(Route.PROFILE) {
            ProfileScreen(
                onBack = { navController.popBackStack() },
                onLogout = {
                    navController.navigate(BottomNavItem.MyPage.route) {
                        popUpTo(0) { inclusive = true }
                    }
                },
                onDeleteAccount = {
                    navController.navigate(BottomNavItem.MyPage.route) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

        composable(Route.SIGNUP) {
            SignUpScreen(
                onBack = { navController.popBackStack() },
                onSignUpSuccess = { navController.popBackStack() }
            )
        }

        composable(Route.SEARCH_MAIN) {
            SearchScreen(
                onBack = { navController.popBackStack() },
                onSearch = onSearchNavigate
            )
        }

        composable(
            route = Route.SEARCH_DETAIL,
            arguments = listOf(
                navArgument("query") { type = NavType.StringType },
                navArgument("fromKeyword") { type = NavType.BoolType }
            )
        ) { backStackEntry ->
            val query = backStackEntry.arguments?.getString("query") ?: ""
            val fromKeyword = backStackEntry.arguments?.getBoolean("fromKeyword") ?: false

            SearchDetailScreen(
                query = query,
                fromKeyword = fromKeyword,
                onBack = { navController.popBackStack() },
                navController = navController,
                onSearch = onSearchNavigate
            )
        }
    }
}