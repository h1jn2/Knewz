package com.example.knewz.ui.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.knewz.data.model.News
import com.example.knewz.data.remote.NewsItem
import com.example.knewz.ui.theme.StrokeGray

@Composable
fun NewsCard(onClick: () -> Unit, news: News) {
   Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, StrokeGray),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        NewsContentSummary(news)
        Log.d("hjn", news.title)
        Log.d("hjn", news.thumbnail ?: "업슴")
        Log.d("hjn", news.url)
    }
}