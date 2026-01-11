package com.example.knewz.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.knewz.ui.theme.StrokeGray

@Composable
fun ScrapToggleButton(
    isScrapped: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (isScrapped) Color.Black  else Color.White
    val contentColor = if (isScrapped) Color.White  else Color.Black
    val strokeColor = if (isScrapped) Color(0xFF7E57C2) else StrokeGray
    val icon = if (isScrapped) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder

    Row(
        modifier = modifier
            .sizeIn(minWidth = 50.dp, minHeight = 24.dp)
            .background(backgroundColor, RoundedCornerShape(10.dp))
            .border(
                BorderStroke(1.dp, strokeColor),
                RoundedCornerShape(10.dp)
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "label",
            modifier = Modifier.size(15.dp),
            tint = contentColor
        )
        Spacer(Modifier.width(4.dp))
        Text(
            text = "스크랩",
            style = MaterialTheme.typography.labelLarge,
            color = contentColor,
        )
    }
}