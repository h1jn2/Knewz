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
                navController.navigate("search/$query") {
                    popUpTo("search/main") { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        }

        composable(BottomNavItem.Home.route) {
            HomeScreen(
                onNavigateToSearch = { navController.navigate("search/main") }
            )
        }
        composable(BottomNavItem.Keyword.route) { KeywordScreen() }
        composable(BottomNavItem.Scrap.route) { ScrapScreen() }
        composable(BottomNavItem.Notif.route) { NotifScreen() }
        composable(BottomNavItem.MyPage.route) {
            LoginScreen(
                onNavigateToSignUp = { navController.navigate("login/signup") },
                onLoginSuccess = {
                    navController.navigate(BottomNavItem.Home.route) {
                        popUpTo(BottomNavItem.Home.route) { inclusive = true }
                    }
                }
            )
        }
        composable(BottomNavItem.MyPage.route) {
            val currentUser = com.google.firebase.auth.FirebaseAuth.getInstance().currentUser

            if (currentUser == null) {
                LoginScreen(
                    onNavigateToSignUp = { navController.navigate("login/signup") },
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

        composable("login/signup") {
            SignUpScreen(
                onBack = { navController.popBackStack() },
                onSignUpSuccess = {
                    navController.popBackStack()
                }
            )
        }
        composable("search/main") {
            SearchScreen(
                onBack = { navController.popBackStack() },
                onSearch = onSearchNavigate
            )
        }
        composable(
            "search/{query}",
            arguments = listOf(navArgument("query") { type = NavType.StringType })
        ) { backStackEntry ->
            val query = backStackEntry.arguments?.getString("query") ?: ""
            SearchDetailScreen(
                query = query,
                onBack = { navController.popBackStack() },
                onSearch = { newQuery ->
                    navController.navigate("search/$newQuery") {
                        popUpTo("search/{query}") { inclusive = true }
                    }
                }
            )
        }
    }
}