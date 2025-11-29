package com.example.knewz.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.outlined.HotelClass
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.knewz.ui.theme.BlueLight
import com.example.knewz.ui.theme.PurpleDark
import com.example.knewz.ui.theme.PurpleLight
import com.example.knewz.ui.theme.PurpleMid

val AI_SUMMARY_BRUSH = Brush.linearGradient(
    colors = listOf(
        PurpleLight,
        BlueLight,
        Color.White
    ),
    start = Offset(0f, 0f),
    end = Offset(1000f, 1400f)
)

@Composable
fun AIQuoteBlock(aiSummaryText: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(brush = AI_SUMMARY_BRUSH)
            .border(
                BorderStroke(1.dp, PurpleDark),
                RoundedCornerShape(12.dp)
            )
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Outlined.HotelClass,
                contentDescription = "AI 요약",
                tint = PurpleMid,
                modifier = Modifier.size(24.dp)
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = "AI 요약",
                style = MaterialTheme.typography.bodyLarge,
                color = PurpleMid
            )
        }
        Spacer(Modifier.height(8.dp))
        Text(
            text = aiSummaryText,
            style = MaterialTheme.typography.bodyMedium,
            color = PurpleMid
        )
    }
}