package com.example.knewz.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.knewz.ui.theme.LightGray
import com.example.knewz.ui.theme.StrokeGray

@Composable
fun NewsStatsBar(views: Int, scraps: Int, shares: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(LightGray, RoundedCornerShape(10.dp))
            .border(
                BorderStroke(1.dp, StrokeGray),
                RoundedCornerShape(10.dp)
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.Visibility,
                contentDescription = "조회 수",
                modifier = Modifier.size(20.dp)
            )
            Spacer(Modifier.width(4.dp))
            Text(
                text = "조회 $views", style = MaterialTheme.typography.bodyLarge,
                color = Color.Black
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        )  {
            Icon(
                imageVector = Icons.Outlined.Bookmark, contentDescription = "스크랩 수",
                modifier = Modifier.size(20.dp)
            )
            Spacer(Modifier.width(4.dp))
            Text(
                text = "스크랩 $scraps", style = MaterialTheme.typography.bodyLarge,
                color = Color.Black
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        )  {
            Icon(
                imageVector = Icons.Outlined.Share, contentDescription = "공유 수",
                modifier = Modifier.size(20.dp)
            )
            Spacer(Modifier.width(4.dp))
            Text(
                text = "공유 $shares", style = MaterialTheme.typography.bodyLarge,
                color = Color.Black
            )
        }
    }
}