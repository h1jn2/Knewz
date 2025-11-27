package com.example.knewz.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.knewz.ui.theme.LightGray
import com.example.knewz.ui.theme.StrokeGray
import com.example.knewz.ui.theme.TextLightGray
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun NewsCard() {
    val imageUrl =
        "https://blog.kakaocdn.net/dna/byIZZL/btsMyrQRWb9/AAAAAAAAAAAAAAAAAAAAAPG3vMG0hs5TXAjhk5fglaLksnKIs1thKShAX_CExXzA/img.png?credential=yqXZFxpELC7KVnFOS48ylbz2pIh7yKj8&expires=1764514799&allow_ip=&allow_referer=&signature=vn0p%2Ft5YNxZGCCnHv3bkWZwFyuU%3D"
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { },
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, StrokeGray),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            GlideImage(
                imageModel = { imageUrl },
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(10.dp)),
                imageOptions = ImageOptions(
                    contentDescription = "뉴스 이미지",
                    contentScale = ContentScale.Crop
                )
            )
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Title",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Company",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Gray,
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Icon(
                        imageVector = Icons.Outlined.History,
                        contentDescription = "Time Before",
                        modifier = Modifier.size(15.dp),
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "30분 전",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Gray,
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Icon(
                        imageVector = Icons.Outlined.Visibility,
                        contentDescription = "Time Before",
                        modifier = Modifier.size(15.dp)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))

                Row {
                    Tag("# AI", LightGray, 10.dp, TextLightGray)
                    Spacer(modifier = Modifier.width(8.dp))
                    Tag("# 인공지능", LightGray, 10.dp, TextLightGray)
                    Spacer(modifier = Modifier.width(8.dp))
                    Tag("# 정치", LightGray, 10.dp, TextLightGray)
                }
            }
        }
    }
}