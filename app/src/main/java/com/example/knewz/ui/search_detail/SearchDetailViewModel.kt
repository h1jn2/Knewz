package com.example.knewz.ui.search_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.knewz.data.model.News
import com.example.knewz.domain.usecase.GetNewsWithThumbnailsUseCase
import com.example.knewz.domain.usecase.ManageSearchHistoryUseCase
import com.example.knewz.domain.usecase.SummarizeNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchDetailViewModel @Inject constructor(
    private val getNewsWithThumbnailsUseCase: GetNewsWithThumbnailsUseCase,
    private val summarizeNewsUseCase: SummarizeNewsUseCase,
    private val manageSearchHistoryUseCase: ManageSearchHistoryUseCase
) : ViewModel() {
    private val _newsList = MutableStateFlow<List<News>>(emptyList())
    val newsList: StateFlow<List<News>> = _newsList
    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText.asStateFlow()

    private val _summary = MutableStateFlow("요약 대기중…")
    val summary = _summary.asStateFlow()

    fun loadNews(query: String = "속보") {
        _searchText.value = query

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

    fun onSearchTextChanged(text: String) {
        _searchText.value = text
    }

    fun executeSearch(onNavigate: (String) -> Unit) {
        val query = _searchText.value
        if (query.isBlank()) return
        viewModelScope.launch {
            manageSearchHistoryUseCase.saveQuery(query)
            onNavigate(query)
        }
    }
}