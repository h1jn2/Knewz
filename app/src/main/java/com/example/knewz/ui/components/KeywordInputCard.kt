package com.example.knewz.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.knewz.ui.keyword.KeywordItem

@Composable
fun KeywordInputCard(
    keyword: String,
    keywordList: List<KeywordItem>,
    onKeywordChange: (String) -> Unit,
    onAddClick: () -> Unit
) {
    val hangulRegex = "^[가-힣a-zA-Z0-9\\s]*$".toRegex()

    val isInvalidFormat = keyword.isNotEmpty() && !hangulRegex.matches(keyword)
    val isInvalidLength = keyword.isNotEmpty() && (keyword.trim().length < 2 || keyword.trim().length > 10)
    val isDuplicate = keywordList.any { it.keyword == keyword.trim() }

    val isError = isInvalidFormat || isDuplicate || (keyword.length > 10)

    val isButtonEnabled = keyword.trim().length in 2..10 && !isDuplicate && !isInvalidFormat

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFF67B3EE).copy(alpha = 0.1f),
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 1.dp,
                color = Color(0xFF67B3EE).copy(alpha = 0.3f),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp)
    ) {
        Text(
            text = "새 키워드 등록",
            style = MaterialTheme.typography.labelLarge,
            color = Color(0xFF455A64)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = keyword,
                onValueChange = {
                    if (it.length <= 11) onKeywordChange(it)
                },
                isError = isError,
                placeholder = {
                    Text(
                        text = "예: 인공지능, 주식",
                        style = MaterialTheme.typography.bodySmall
                    )
                },
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                shape = RoundedCornerShape(10.dp),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedBorderColor = Color(0xFF4A90E2),
                    unfocusedBorderColor = Color.LightGray.copy(alpha = 0.5f),
                    errorBorderColor = Color.Red.copy(alpha = 0.5f),
                    cursorColor = Color(0xFF4A90E2)
                )
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = onAddClick,
                enabled = isButtonEnabled,
                modifier = Modifier.height(56.dp),
                shape = RoundedCornerShape(10.dp),
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4A90E2),
                    disabledContainerColor = Color(0xFF4A90E2).copy(alpha = 0.3f),
                    contentColor = Color.White,
                    disabledContentColor = Color.White.copy(alpha = 0.6f)
                )
            ) {
                Icon(
                    imageVector = Icons.Outlined.Add,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
            }
        }

        if (isError || (isInvalidLength && keyword.isNotEmpty())) {
            val errorMessage = when {
                isDuplicate -> "* 이미 등록된 키워드입니다."
                isInvalidFormat -> "* 완성된 한글로 입력해주세요."
                isInvalidLength -> "* 2자~10자 사이로 입력해주세요."
                else -> ""
            }
            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = Color.Red.copy(alpha = 0.8f),
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(top = 4.dp, start = 4.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "관심있는 키워드를 등록하면 새 뉴스 알림을 받을 수 있어요",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )
    }
}