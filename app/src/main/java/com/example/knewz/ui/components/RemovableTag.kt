package com.example.knewz.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.knewz.ui.theme.StrokeGray

@Composable
fun RemovableTag(
    tagName: String,
    onClick: () -> Unit,
    onRemove: () -> Unit
) {
    Row(
        modifier = Modifier
            .sizeIn(minWidth = 50.dp, minHeight = 40.dp)
            .background(Color.White, RoundedCornerShape(20.dp))
            .clickable { onClick() }
            .border(
                BorderStroke(1.dp, StrokeGray),
                RoundedCornerShape(20.dp)
            )
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Clear,
            contentDescription = "Clear",
            modifier = Modifier
                .size(18.dp)
                .clickable { onRemove() }
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = tagName,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black
        )
    }
}