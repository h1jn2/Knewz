package com.example.knewz.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.knewz.ai.GeminiHelper
import com.example.knewz.data.model.News
import com.example.knewz.data.remote.NewsItem
import com.example.knewz.domain.usecase.GetArticleTextUseCase
import com.example.knewz.domain.usecase.GetNewsUseCase
import com.example.knewz.domain.usecase.GetNewsWithThumbnailsUseCase
import com.example.knewz.domain.usecase.SummarizeNewsUseCase
import com.example.knewz.util.getThumbnailUrl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNewsWithThumbnailsUseCase: GetNewsWithThumbnailsUseCase,
    private val summarizeNewsUseCase: SummarizeNewsUseCase
) : ViewModel() {

    private val _newsList = MutableStateFlow<List<News>>(emptyList())
    val newsList: StateFlow<List<News>> = _newsList

    private val _summary = MutableStateFlow("요약 대기중…")
    val summary = _summary.asStateFlow()

    fun loadNews(query: String = "속보") {
        viewModelScope.launch {
            _newsList.value = getNewsWithThumbnailsUseCase.execute(query)
        }
    }

    fun summarizeNewsItem(news: News) {
        viewModelScope.launch {
            val result = summarizeNewsUseCase.execute(news) { status ->
                _summary.value = status
            }
            _summary.value = result
        }
    }
}