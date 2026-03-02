package com.example.knewz.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.LocalOffer
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.knewz.ui.keyword.KeywordItem
import com.example.knewz.util.formatTimestamp

@Composable
fun KeywordCard(
    item: KeywordItem,
    onCardClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onToggleClick: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onCardClick() },
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.25f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier.size(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.LocalOffer,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(Modifier.width(8.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.keyword,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black,
                    maxLines = 1
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    text = formatTimestamp(item.createdAt),
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Gray
                )
            }

            Spacer(Modifier.width(12.dp))

            Text(
                text = if (item.notifyEnabled) "ON" else "OFF",
                style = MaterialTheme.typography.labelMedium,
                color = if (item.notifyEnabled) Color(0xFF67B3EE) else Color.Gray
            )

            Spacer(Modifier.width(6.dp))

            Switch(
                checked = item.notifyEnabled,
                onCheckedChange = onToggleClick,
                modifier = Modifier
                    .scale(0.7f)
                    .padding(horizontal = 0.dp)
                    .height(20.dp),
                colors = SwitchDefaults.colors(
                    checkedTrackColor = Color(0xFF67B3EE),
                    checkedThumbColor = Color.White
                )
            )

            Spacer(Modifier.width(4.dp))

            IconButton(
                onClick = onDeleteClick,
                modifier = Modifier.size(32.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "삭제",
                    tint = Color.Gray,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}
