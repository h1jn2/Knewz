package com.example.knewz.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.knewz.R
import com.example.knewz.ui.theme.TextMediumGray

@Composable
fun LoginHeader(title: String, content: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.knewz_icon),
            contentDescription = "App Icon",
            modifier = Modifier.size(80.dp)
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = "키워드로 보는 내 뉴스",
            style = MaterialTheme.typography.titleSmall,
            color = TextMediumGray
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = "키워드로 보는 내 뉴스",
            style = MaterialTheme.typography.titleSmall,
            color = TextMediumGray
        )
    }
}