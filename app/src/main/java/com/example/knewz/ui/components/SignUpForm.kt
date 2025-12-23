package com.example.knewz.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.knewz.R
import com.example.knewz.ui.theme.StrokeGray
import com.example.knewz.ui.theme.TextMediumGray

@Composable
fun SignUpForm() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Text(
        text = "계정 만들기",
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )
    Spacer(Modifier.height(4.dp))
    Text(
        text = "정보를 입력하여 계정을 만드세요",
        style = MaterialTheme.typography.bodyMedium,
        color = TextMediumGray
    )

    Spacer(Modifier.height(16.dp))
    InputField(
        label = "이름",
        placeholder = "홍길동",
        value = email,
        onValueChange = { email = it },
        leadingIcon = {
            Icon(Icons.Outlined.PersonOutline, contentDescription = null)
        }
    )
    Spacer(Modifier.height(16.dp))
    InputField(
        label = "이메일",
        placeholder = "example@email.com",
        value = email,
        onValueChange = { email = it },
        leadingIcon = {
            Icon(Icons.Outlined.Email, contentDescription = null)
        }
    )
    Spacer(Modifier.height(16.dp))
    InputField(
        label = "비밀번호",
        placeholder = "8자 이상 입력",
        value = password,
        onValueChange = { password = it },
        isPassword = true,
        leadingIcon = {
            Icon(Icons.Outlined.Lock, contentDescription = null)
        }
    )
    Spacer(Modifier.height(16.dp))
    InputField(
        label = "비밀번호 확인",
        placeholder = "비밀번호 재입력",
        value = password,
        onValueChange = { password = it },
        isPassword = true,
        leadingIcon = {
            Icon(Icons.Outlined.Lock, contentDescription = null)
        }
    )

    Spacer(Modifier.height(16.dp))

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        onClick = {},
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
    ) {
        Text("회원가입", style = MaterialTheme.typography.bodyLarge)
    }

}