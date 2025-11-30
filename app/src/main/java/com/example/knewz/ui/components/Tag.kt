package com.example.knewz.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.knewz.ui.theme.LightGray
import com.example.knewz.ui.theme.TextLightGray

@Composable
fun Tag(tagName: String, backgroundColor: Color, borderRound: Dp, textColor: Color, borderColor: Color = LightGray) {
    Box(
        modifier = Modifier
            .sizeIn(minWidth = 50.dp, minHeight = 24.dp)
            .background(backgroundColor, RoundedCornerShape(borderRound))
            .border(
                BorderStroke(1.dp, borderColor),
                RoundedCornerShape(borderRound)
            )
            .padding(horizontal = 8.dp, vertical = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = tagName,
            style = MaterialTheme.typography.labelLarge,
            color = textColor
        )
    }
}