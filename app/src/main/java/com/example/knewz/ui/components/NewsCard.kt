package com.example.knewz.ui.components

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
fun NewsCard(onClick: () -> Unit, news: NewsItem) {
    val imageUrl =
        "https://blog.kakaocdn.net/dna/byIZZL/btsMyrQRWb9/AAAAAAAAAAAAAAAAAAAAAPG3vMG0hs5TXAjhk5fglaLksnKIs1thKShAX_CExXzA/img.png?credential=yqXZFxpELC7KVnFOS48ylbz2pIh7yKj8&expires=1764514799&allow_ip=&allow_referer=&signature=vn0p%2Ft5YNxZGCCnHv3bkWZwFyuU%3D"
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, StrokeGray),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        NewsContentSummary(imageUrl)
    }
}