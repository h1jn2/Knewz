package com.example.knewz.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.knewz.ai.GeminiHelper
import com.example.knewz.data.model.News
import com.example.knewz.data.remote.NewsItem
import com.example.knewz.domain.usecase.GetArticleTextUseCase
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
    private val getNewsUseCase: GetNewsUseCase,
    private val getArticleTextUseCase: GetArticleTextUseCase
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
    fun summarizeNewsItem(news: News) {
        viewModelScope.launch {
            _summary.value = "기사 본문 로드 및 요약 대기중..."

            val articleText = getArticleTextUseCase.execute(news.url)

            if (articleText.length < 50) {
                _summary.value = "본문 텍스트가 너무 짧거나(길이: ${articleText.length}) 유효하지 않습니다."
                return@launch
            }

            try {
                _summary.value = GeminiHelper.summarize(articleText)
                Log.d("GeminiOutput", "--- 5. 요약 성공 및 업데이트 완료")
            } catch (e: Exception) {
                Log.e("GeminiOutput", "--- 6. Gemini API 호출 중 오류 발생: ${e.message}", e)
                _summary.value = "요약 서버 오류: ${e.message}"
            }
        }
    }
}