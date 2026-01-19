package com.example.knewz.ui.search_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.knewz.data.model.News
import com.example.knewz.ui.components.NewsCard
import com.example.knewz.ui.components.NewsDetailSheet
import com.example.knewz.ui.components.SearchBox

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchDetailScreen(
    query: String,
    fromKeyword: Boolean = false,
    viewModel: SearchDetailViewModel = hiltViewModel(),
    navController: NavController,
    onBack: () -> Unit,
    onSearch: (String) -> Unit
) {
    val newsList by viewModel.newsList.collectAsState()
    val summary by viewModel.summary.collectAsState()
    var showSheet by remember { mutableStateOf(false) }
    var clickedNews: News? by remember { mutableStateOf(null) }
    val searchText by viewModel.searchText.collectAsState()

    LaunchedEffect(query) {
        viewModel.loadNews(query)
    }

    LaunchedEffect(clickedNews) {
        clickedNews?.let {
            viewModel.summarizeNewsItem(it)
        }
    }
    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        containerColor = Color.White,
        topBar = {
            if (fromKeyword) {
                CenterAlignedTopAppBar(
                    title = {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "#$query",
                                style = MaterialTheme.typography.titleMedium,
                                color = Color(0xFF4A90E2)
                            )
                            Text(
                                text = "키워드 뉴스 결과",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.Gray
                            )
                        }
                    },
                    windowInsets = WindowInsets(0, 0, 0, 0),
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(imageVector = Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = null)
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.White)
                )
            } else {
                SearchBox(
                    searchText = searchText,
                    onSearchTextChanged = viewModel::onSearchTextChanged,
                    onSearchAction = {
                        viewModel.executeSearch(onNavigate = onSearch)
                    },
                    onBack = onBack,
                )
            }
        }
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
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding(),
                    start = 20.dp,
                    end = 20.dp
                ),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(newsList) { news ->
                    NewsCard(
                        onClick = {
                            showSheet = true
                            clickedNews = news
                        },
                        news = news
                    )
                }
            }
        }
        NewsDetailSheet(
            aiSummaryText = summary,
            news = clickedNews,
            isVisible = showSheet,
            navController = navController,
            onDismissRequest = {
                showSheet = false
            }
        )
    }
}