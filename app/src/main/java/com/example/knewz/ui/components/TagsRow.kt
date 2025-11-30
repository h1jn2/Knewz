package com.example.knewz.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.knewz.ui.theme.LightGray
import com.example.knewz.ui.theme.TextLightGray

@Composable
fun TagsRow() {
    Row {
        Tag("# AI", LightGray, 10.dp, TextLightGray)
        Spacer(modifier = Modifier.width(8.dp))
        Tag("# 인공지능", LightGray, 10.dp, TextLightGray)
        Spacer(modifier = Modifier.width(8.dp))
        Tag("# 정치", LightGray, 10.dp, TextLightGray)
    }
}