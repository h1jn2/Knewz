package com.example.knewz.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.knewz.util.getRelativeTime
import java.util.Date

@Composable
fun NewsMetaRow(company: String, publishedAt: Date?) {
    val relativeTime = getRelativeTime(publishedAt)

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = company,
            style = MaterialTheme.typography.labelLarge,
            color = Color.Gray,
        )
        Spacer(modifier = Modifier.width(12.dp))
        if (publishedAt != null && relativeTime.isNotEmpty()) {
            Spacer(modifier = Modifier.width(12.dp))
            Icon(
                imageVector = Icons.Outlined.History,
                contentDescription = "Time Before",
                modifier = Modifier.size(15.dp),
                tint = Color.Gray
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = relativeTime,
                style = MaterialTheme.typography.labelLarge,
                color = Color.Gray,
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Icon(
            imageVector = Icons.Outlined.Visibility,
            contentDescription = "View",
            modifier = Modifier.size(15.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = "1,234",
            style = MaterialTheme.typography.labelLarge,
            color = Color.Gray,
        )
    }

}