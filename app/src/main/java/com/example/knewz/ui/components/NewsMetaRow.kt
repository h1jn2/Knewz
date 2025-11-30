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

@Composable
fun NewsMetaRow() {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Company",
            style = MaterialTheme.typography.labelLarge,
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
            style = MaterialTheme.typography.labelLarge,
            color = Color.Gray,
        )
        Spacer(modifier = Modifier.width(12.dp))
        Icon(
            imageVector = Icons.Outlined.Visibility,
            contentDescription = "Time Before",
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