package com.example.knewz.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.knewz.ui.theme.AccentBlue

@Composable
fun RealtimeSearchBox(
    searchText: String,
    onSearchTextChanged: (String) -> Unit,
    isDescending: Boolean, // true: 최신순, false: 오래된순
    onToggleSort: () -> Unit // 정렬 전환 함수
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = searchText,
            onValueChange = {
                onSearchTextChanged(it)
            },
            placeholder = { Text("검색어를 입력하세요...", style = MaterialTheme.typography.bodyMedium) },
            singleLine = true,
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(28.dp),
            leadingIcon = {
                Icon(Icons.Outlined.Search, contentDescription = null, tint = Color.Gray)
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = AccentBlue,
                unfocusedBorderColor = Color.LightGray.copy(alpha = 0.5f),
                focusedContainerColor = Color(0xFFF5F5F5),
                unfocusedContainerColor = Color(0xFFF5F5F5)
            )
        )

        Spacer(Modifier.width(8.dp))

        TextButton(
            onClick = onToggleSort,
            contentPadding = PaddingValues(horizontal = 12.dp),
            colors = ButtonDefaults.textButtonColors(contentColor = Color.Black)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = if (isDescending) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Text(
                    text = if (isDescending) "최신순" else "오래된순",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}