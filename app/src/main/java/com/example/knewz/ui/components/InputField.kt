package com.example.knewz.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun InputField(
    label: String,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    isPassword: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    errorMessage: String? = null
) {
    var passwordVisible by remember { mutableStateOf(false) }
    val isError = errorMessage != null

    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            color = Color.Black
        )

        Spacer(Modifier.height(6.dp))

        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 48.dp),
            isError = isError,
            placeholder = {
                Text(text = placeholder, color = Color.Gray)
            },
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            leadingIcon = leadingIcon,
            trailingIcon = {
                if (isPassword) {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = if (passwordVisible)
                                Icons.Outlined.Visibility
                            else
                                Icons.Outlined.VisibilityOff,
                            contentDescription = null
                        )
                    }
                }
            },
            visualTransformation =
                if (isPassword && !passwordVisible)
                    PasswordVisualTransformation()
                else
                    VisualTransformation.None,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF2F2F2),
                unfocusedContainerColor = Color(0xFFF2F2F2),

                focusedIndicatorColor = if (isError) Color.Red else Color.Transparent,
                unfocusedIndicatorColor = if (isError) Color.Red else Color.Transparent,

                errorIndicatorColor = Color.Red,
                errorContainerColor = Color(0xFFF2F2F2),
                errorCursorColor = Color.Red
            )
        )
        if (isError) {
            Text(
                text = errorMessage!!,
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 4.dp, top = 4.dp)
            )
        }
    }
}
