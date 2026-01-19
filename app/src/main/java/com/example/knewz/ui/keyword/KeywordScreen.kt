package com.example.knewz.ui.keyword

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Tag
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.example.knewz.ui.components.KeywordCard
import com.example.knewz.ui.components.KeywordInputCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KeywordScreen() {
    var keyword by remember { mutableStateOf("") }
    var keywordList by remember { mutableStateOf(listOf<KeywordItem>()) }

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
                Icon(imageVector = Icons.Outlined.Tag, contentDescription = null)
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "내 키워드", style = MaterialTheme.typography.bodyLarge)
            }
            Spacer(modifier = Modifier.height(16.dp))

            KeywordInputCard(
                keyword = keyword,
                onKeywordChange = { keyword = it },
                onAddClick = {
                    if (keyword.isNotBlank()) {
                        val newItem = KeywordItem(
                            name = keyword,
                            date = "2026.01.19"
                        )
                        keywordList = keywordList + newItem
                        keyword = ""
                    }
                }
            )
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize().weight(1f),
                contentPadding = PaddingValues(bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(keywordList) { item ->
                    KeywordCard(
                        item = item,
                        onDeleteClick = {
                            keywordList = keywordList.filter { it.id != item.id }
                        },
                        onToggleClick = { isOn ->
                            keywordList = keywordList.map {
                                if (it.id == item.id) it.copy(isAlertOn = isOn) else it
                            }
                        }
                    )
                }
            }
        }
    }
}