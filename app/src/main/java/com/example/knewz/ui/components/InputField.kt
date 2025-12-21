package com.example.knewz.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun InputField(label: String, placeholder: String, isPassword: Boolean = false) {
    Column {
        Text(text = label, style = MaterialTheme.typography.labelLarge, color = Color.Black)
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(placeholder, color = Color.LightGray) },
            shape = RoundedCornerShape(12.dp),
            singleLine = true
        )
    }
}