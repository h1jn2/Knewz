package com.example.knewz.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.knewz.ai.GeminiHelper
import com.example.knewz.data.model.News
import com.example.knewz.data.remote.NewsItem
import com.example.knewz.domain.usecase.GetNewsUseCase
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
    private val getNewsUseCase: GetNewsUseCase
) : ViewModel() {

    private val _newsList = MutableStateFlow<List<News>>(emptyList())
    val newsList: StateFlow<List<News>> = _newsList

    private val _summary = MutableStateFlow("요약 대기중…")
    val summary = _summary.asStateFlow()

    fun loadNews(query: String = "속보") {
        viewModelScope.launch {
            val result = getNewsUseCase.execute(query)
            result.onSuccess { list ->
                _newsList.value = list

                list.forEachIndexed { index, news ->
                    launch(Dispatchers.IO) {
                        val img = getThumbnailUrl(news.url)
                        if (img != null) {
                            val updated = _newsList.value.toMutableList()
                            updated[index] = news.copy(thumbnail = img)
                            _newsList.value = updated
                        }
                    }
                }
            }
        }
    }
    fun summarize(article: String) {
        viewModelScope.launch {
            try {
                _summary.value = GeminiHelper.summarize(article)
            } catch (e: Exception) {
                _summary.value = "오류 발생: ${e.message}"
            }
        }
    }
}