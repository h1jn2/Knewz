package com.example.knewz.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.knewz.ui.theme.AccentBlue
import com.example.knewz.ui.theme.LightGray

@Composable
fun SearchBox(
    searchText: String,
    onSearchTextChanged: (String) -> Unit,
    onSearchAction: (String) -> Unit,
    onBack: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onBack
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                contentDescription = "Back"
            )
        }
        Spacer(Modifier.width(4.dp))
        Surface(
            modifier = Modifier.weight(1f),
            color = Color.White
        ) {
            OutlinedTextField(
                value = searchText,
                onValueChange = onSearchTextChanged,
                placeholder = { Text("검색어를 입력하세요...") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(28.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = AccentBlue,
                    unfocusedIndicatorColor = LightGray,
                    disabledIndicatorColor = LightGray,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                ),
                keyboardActions = KeyboardActions(
                    onSearch = { onSearchAction(searchText) }
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                )
            )
        }
        Spacer(Modifier.width(4.dp))
        IconButton(
            onClick = { onSearchAction(searchText) }
        ) {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = "Search"
            )
        }
    }
}