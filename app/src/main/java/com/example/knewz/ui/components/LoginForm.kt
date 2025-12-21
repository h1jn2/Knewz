package com.example.knewz.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.knewz.ui.theme.StrokeGray
import com.example.knewz.ui.theme.TextMediumGray

@Composable
fun LoginForm() {
    Text(text = "로그인", style = MaterialTheme.typography.titleLarge, color = Color.Black)
    Spacer(Modifier.height(4.dp))
    Text(
        text = "계정에 로그인하여 맞춤 뉴스를 받아보세요",
        style = MaterialTheme.typography.bodyMedium,
        color = TextMediumGray
    )

    Spacer(Modifier.height(16.dp))

    InputField(label = "이메일", placeholder = "example@email.com")
    Spacer(Modifier.height(16.dp))
    InputField(label = "비밀번호", placeholder = "••••••••", isPassword = true)

    Spacer(Modifier.height(16.dp))

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        onClick = {},
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
    ) {
        Text("로그인", style = MaterialTheme.typography.bodyLarge)
    }

    Spacer(Modifier.height(16.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            thickness = 1.dp,
            color = StrokeGray
        )

        Text(
            text = "또는",
            modifier = Modifier.padding(horizontal = 16.dp),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )

        HorizontalDivider(
            modifier = Modifier.weight(1f),
            thickness = 1.dp,
            color = StrokeGray
        )
    }

    Spacer(Modifier.height(16.dp))

    OutlinedButton(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        onClick = {},
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, StrokeGray)
    ) {
        Text("구글로 계속하기", color = Color.Black)
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "계정이 없으신가요?",
            style = MaterialTheme.typography.bodySmall,
            color = TextMediumGray
        )
        Spacer(Modifier.width(4.dp))
        TextButton(onClick = { }) {
            Text("회원가입", fontWeight = FontWeight.Bold, color = Color.Black)
        }
    }
}