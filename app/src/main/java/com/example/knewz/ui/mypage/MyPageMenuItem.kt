package com.example.knewz.ui.mypage

import androidx.compose.ui.graphics.vector.ImageVector

data class MyPageMenuItem(
    val title: String,
    val subtitle: String,
    val icon: ImageVector,
    val route: String
)