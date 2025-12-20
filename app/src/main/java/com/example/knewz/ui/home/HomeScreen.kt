package com.example.knewz.ui.home

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.HotelClass
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.knewz.data.model.News
import com.example.knewz.ui.components.NewsCard
import com.example.knewz.ui.components.NewsDetailSheet
import com.example.knewz.ui.components.Tag
import com.example.knewz.ui.theme.AccentPurple
import com.example.knewz.ui.theme.StrokeGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToSearch: () -> Unit
) {
    val newsList by viewModel.newsList.collectAsState()
    val summary by viewModel.summary.collectAsState()
    var showSheet by remember { mutableStateOf(false) }
    var clickedNews: News? by remember { mutableStateOf(null) }
    val interactionSource = remember { MutableInteractionSource() }

    LaunchedEffect(Unit) {
        viewModel.loadNews()
    }

    LaunchedEffect(clickedNews) {
        clickedNews?.let {
            viewModel.summarizeNewsItem(it)
        }
    }

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
                    containerColor = Color.White
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
        containerColor = Color.White
    ) { paddingValues ->
        if (newsList.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = paddingValues.calculateTopPadding(),
                        bottom = paddingValues.calculateBottomPadding()
                    ),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White, RoundedCornerShape(10.dp))
                            .border(BorderStroke(1.dp, StrokeGray), RoundedCornerShape(10.dp))
                            .padding(12.dp)
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null,
                                onClick = onNavigateToSearch,
                            )
                    ) {
                        Icon(imageVector = Icons.Outlined.Search, contentDescription = "Search")
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "키워드를 검색해보세요.",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.DarkGray
                        )
                    }
                }

                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.HotelClass,
                            contentDescription = "AI",
                            tint = AccentPurple
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "맞춤 추천 뉴스",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Tag("AI 기반", Color.White, 5.dp, Color.Black, StrokeGray)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
//                items(newsList) { news ->
//                    NewsCard(
//                        onClick = {
//                            showSheet = true
//                        },
//                        news = news
//                    )
//                }
                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(imageVector = Icons.Outlined.History, contentDescription = "AI")
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "최신 뉴스",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Black
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                }
                items(newsList.take(3)) { news ->
                    NewsCard(
                        onClick = {
                            showSheet = true
                            clickedNews = news
                        },
                        news = news
                    )
                }
            }
            NewsDetailSheet(
                aiSummaryText = summary,
                news = clickedNews,
                isVisible = showSheet,
                onDismissRequest = {
                    showSheet = false
                }
            )
        }
    }
}