package com.example.knewz.ui.scrap

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.knewz.data.model.News
import com.example.knewz.ui.components.NewsCard
import com.example.knewz.ui.components.NewsDetailSheet
import com.example.knewz.ui.components.RealtimeSearchBox
import com.example.knewz.ui.components.Tag
import com.example.knewz.ui.theme.StrokeGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScrapScreen(
    viewModel: ScrapViewModel = hiltViewModel(),
    navController: NavController
) {
    val searchText by viewModel.searchText.collectAsState()
    val isDescending by viewModel.isDescending.collectAsState()
    val searchResults by viewModel.searchResults.collectAsState()
    val summary by viewModel.summary.collectAsState()

    var showSheet by remember { mutableStateOf(false) }
    var clickedNews: News? by remember { mutableStateOf(null) }

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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding()
                ),
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Outlined.Bookmarks, contentDescription = null)
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "내 스크랩", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.weight(1f))
                Tag("${searchResults.size} 개", Color.White, 5.dp, Color.Black, StrokeGray)
            }

            Spacer(modifier = Modifier.height(16.dp))
            RealtimeSearchBox(
                searchText = searchText,
                onSearchTextChanged = viewModel::onSearchTextChanged,
                isDescending = isDescending,
                onToggleSort = viewModel::toggleSort
            )
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(searchResults) { news ->
                    NewsCard(
                        news = news,
                        onClick = {
                            clickedNews = news
                            showSheet = true
                        }
                    )
                }

                if (searchResults.isEmpty()) {
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 80.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            if (searchText.isBlank()) {
                                Text(
                                    text = "스크랩한 뉴스가 없습니다.",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = Color.Gray,
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = "관심 있는 뉴스를 스크랩해 보세요.",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.LightGray,
                                    textAlign = TextAlign.Center
                                )
                            } else {
                                Text(
                                    text = "'$searchText'에 대한\n검색 결과가 없습니다.",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = Color.Gray,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }
            if (showSheet) {
                NewsDetailSheet(
                    aiSummaryText = summary,
                    news = clickedNews,
                    isVisible = showSheet,
                    navController = navController,
                    onDismissRequest = {
                        showSheet = false
                        viewModel.resetSummary()
                    }
                )
            }
        }
    }
}