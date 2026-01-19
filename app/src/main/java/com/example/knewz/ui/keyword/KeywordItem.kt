package com.example.knewz.ui.keyword

data class KeywordItem(
    val id: String = java.util.UUID.randomUUID().toString(),
    val name: String,
    val date: String,
    val isAlertOn: Boolean = true
)