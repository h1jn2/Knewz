package com.example.knewz.ui.mypage

import android.view.MenuItem
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.knewz.ui.components.Tag
import com.example.knewz.ui.theme.StrokeGray
import com.example.knewz.ui.theme.TextLightGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPageScreen(
    viewModel: MyPageViewModel = hiltViewModel(),
    onNavigate: (String) -> Unit
) {
    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Knewz",
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                colors = topAppBarColors(
                    containerColor = White
                ),
                windowInsets = WindowInsets(0, 0, 0, 0),
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color.LightGray.copy(alpha = 0.4f),
                        shape = RectangleShape
                    )
            )
        },
        containerColor = White
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(White)
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(Modifier.height(16.dp))
            OutlinedCard(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, StrokeGray),
                colors = CardDefaults.outlinedCardColors(containerColor = White)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp)
                ) {
                    Text(
                        text = viewModel.userName,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = Black
                    )
                    Text(
                        text = viewModel.userEmail,
                        style = MaterialTheme.typography.bodySmall,
                        color = TextLightGray
                    )

                    Spacer(Modifier.height(16.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Tag("스크랩 0개", StrokeGray, 8.dp, Black, StrokeGray)
                        Tag("키워드 0개", StrokeGray, 8.dp, Black, StrokeGray)
                    }
                }
            }
            Spacer(Modifier.height(20.dp))
            OutlinedCard(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, StrokeGray),
                colors = CardDefaults.outlinedCardColors(containerColor = White)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val menuList = listOf(
                        MyPageMenuItem("프로필", "내 정보 관리", Icons.Outlined.Person, "profile"),
                        MyPageMenuItem("설정", "알림 및 환경설정", Icons.Outlined.Settings, "setting"),
                        MyPageMenuItem("통계", "사용 기록 및 분석", Icons.Outlined.BarChart, "stat")
                    )

                    itemsIndexed(menuList) { index, item ->
                        MyPageRow(
                            title = item.title,
                            subtitle = item.subtitle,
                            icon = item.icon,
                            onClick = { onNavigate(item.route) }
                        )

                        if (index < menuList.lastIndex) {
                            HorizontalDivider(
                                modifier = Modifier.padding(horizontal = 16.dp),
                                thickness = 0.5.dp,
                                color = StrokeGray.copy(alpha = 0.5f)
                            )
                        }
                    }
                }
            }
        }
    }
}

