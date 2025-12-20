package com.example.knewz.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.knewz.ui.components.RemovableTagsRow
import com.example.knewz.ui.components.SearchBox

@Composable
fun SearchScreen(
    searchViewModel: SearchViewModel = hiltViewModel(),
    onBack: () -> Unit,
    onSearch: (String) -> Unit
) {
    val searchText by searchViewModel.searchText.collectAsState()
    val tagList = listOf("검색어", "검색어", "검색어", "검색어", "검색어", "검색어")
    Scaffold(
        containerColor = Color.White,
        topBar = {
            SearchBox(
                searchText = searchText,
                onSearchTextChanged = searchViewModel::onSearchTextChanged,
                onSearchAction = {
                    searchViewModel.executeSearch(onNavigate = onSearch)
                },
                onBack = onBack,
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "최근 검색어",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black
            )
            RemovableTagsRow(tagList)
        }
    }
}