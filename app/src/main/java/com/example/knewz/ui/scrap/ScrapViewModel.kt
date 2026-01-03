package com.example.knewz.ui.scrap

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.knewz.data.model.News
import com.example.knewz.data.remote.NewsItem
import com.example.knewz.data.repository.NewsRepository
import com.example.knewz.domain.usecase.SummarizeNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScrapViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val summarizeNewsUseCase: SummarizeNewsUseCase
) : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    // 정렬 상태 (true: 최신순, false: 오래된순)
    private val _isDescending = MutableStateFlow(true)
    val isDescending = _isDescending.asStateFlow()

    private val _summary = MutableStateFlow("요약 대기중…")
    val summary = _summary.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val searchResults = combine(_searchText, _isDescending) { query, isDesc ->
        query to isDesc
    }.debounce(400L)
        .flatMapLatest { (query, isDesc) ->
            flow {
                if (query.isBlank()) {
                    emit(emptyList<News>())
                } else {
                    emit(newsRepository.searchNews(query, isDesc))
                }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun summarizeNewsItem(news: News) {
        viewModelScope.launch {
            _summary.value = "요약 중..."
            val result = summarizeNewsUseCase.execute(news) { status ->
                _summary.value = status
            }
            _summary.value = result
        }
    }

    fun resetSummary() {
        _summary.value = "요약 대기중…"
    }
    fun onSearchTextChanged(newText: String) {
        _searchText.value = newText
    }

    fun toggleSort() {
        _isDescending.value = !_isDescending.value
    }
}