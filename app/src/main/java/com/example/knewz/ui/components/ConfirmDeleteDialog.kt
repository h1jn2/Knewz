package com.example.knewz.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun ConfirmDeleteDialog(onConfirm: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("회원 탈퇴") },
        text = { Text("정말로 탈퇴하시겠습니까? 모든 데이터가 삭제되며 복구할 수 없습니다.") },
        confirmButton = {
            TextButton(onClick = onConfirm) { Text("탈퇴", color = Color.Red) }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("취소") }
        }
    )
}