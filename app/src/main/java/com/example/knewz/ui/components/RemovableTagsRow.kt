package com.example.knewz.ui.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RemovableTagsRow(
    tags: List<String>,
    onTagClick: (String) -> Unit,
    onRemoveClick: (String) -> Unit
) {
    val scrollState = rememberScrollState()
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(scrollState),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        tags.forEach { tag ->
            RemovableTag(
                tagName = tag,
                onClick = { onTagClick(tag) },
                onRemove = { onRemoveClick(tag) }
            )
        }
    }
}