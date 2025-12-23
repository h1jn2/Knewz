package com.example.knewz.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.knewz.ui.components.LoginCard
import com.example.knewz.ui.components.LoginForm
import com.example.knewz.ui.components.LoginHeader
import com.example.knewz.ui.theme.AI_SUMMARY_BRUSH

@Composable
fun LoginScreen(onNavigateToSignUp: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = AI_SUMMARY_BRUSH)
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoginHeader(true,"키워드로 보는 내 뉴스")
        Spacer(Modifier.height(24.dp))
        LoginCard {
            LoginForm(onNavigateToSignUp)
        }
    }
}

