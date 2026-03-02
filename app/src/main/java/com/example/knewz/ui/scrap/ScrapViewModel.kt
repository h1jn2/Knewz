package com.example.knewz.ui.scrap

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.knewz.data.model.News
import com.example.knewz.data.remote.NewsItem
import com.example.knewz.data.repository.NewsRepository
import com.example.knewz.domain.usecase.SummarizeNewsUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
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
    private val summarizeNewsUseCase: SummarizeNewsUseCase,
    private val auth: FirebaseAuth,
    private val database: FirebaseDatabase,
) : ViewModel() {
    private val _allScraps = MutableStateFlow<List<News>>(emptyList())

    private var scrapListener: ValueEventListener? = null
    private var currentScrapRef: DatabaseReference? = null

    private val _isScrapped = MutableStateFlow(false)
    val isScrapped = _isScrapped.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    // 정렬 상태 (true: 최신순, false: 오래된순)
    private val _isDescending = MutableStateFlow(true)
    val isDescending = _isDescending.asStateFlow()

    private val _summary = MutableStateFlow("요약 대기중…")
    val summary = _summary.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val searchResults = combine(_allScraps, _searchText, _isDescending) { scraps, query, isDesc ->
        val filtered = if (query.isBlank()) {
            scraps
        } else {
            scraps.filter { it.title.contains(query, ignoreCase = true) }
        }

        if (isDesc) filtered else filtered.reversed()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun observeScrappedList() {
        val uid = auth.currentUser?.uid ?: return
        val scrapsRef = database.getReference("users/$uid/scraps")

        scrapsRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val items = mutableListOf<News>()
                snapshot.children.forEach { child ->
                    val news = News(
                        title = child.child("title").value.toString(),
                        source = child.child("source").value.toString(),
                        url = child.child("url").value.toString(),
                        thumbnail = child.child("thumbnail").value.toString(),
                        content = "",
                    )
                    items.add(news)
                }
                // 2. 정렬 상태에 따라 데이터 업데이트 (최신순 등)
                _allScraps.value = items.reversed()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("ScrapVM", "데이터 읽기 실패: ${error.message}")
            }
        })
    }

    fun observeScrapStatus(news: News) {
        val user = auth.currentUser
        if (user == null) {
            _isScrapped.value = false
            return
        }

        val uid = user.uid
        val scrapId = news.url.hashCode().toString()

        scrapListener?.let { currentScrapRef?.removeEventListener(it) }

        currentScrapRef = database.getReference("users/$uid/scraps/$scrapId")
        scrapListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                _isScrapped.value = snapshot.exists()
            }
            override fun onCancelled(error: DatabaseError) {}
        }
        currentScrapRef?.addValueEventListener(scrapListener!!)
    }

    fun toggleScrap(news: News, onResult: (Boolean, String?) -> Unit) {
        val user = auth.currentUser
        if (user == null) {
            onResult(false, "로그인이 필요합니다.")
            return
        }

        val uid = user.uid
        val scrapId = news.url.hashCode().toString()
        val scrapRef = database.getReference("users/$uid/scraps/$scrapId")

        scrapRef.get().addOnSuccessListener { snapshot ->
            if (snapshot.exists()) {
                scrapRef.removeValue().addOnSuccessListener {
                    _isScrapped.value = false
                    onResult(false, null)
                }
            } else {
                val scrapData = mapOf(
                    "newsId" to scrapId,
                    "title" to news.title,
                    "thumbnail" to (news.thumbnail ?: ""),
                    "source" to news.source,
                    "url" to news.url,
                    "createdAt" to System.currentTimeMillis()
                )
                scrapRef.setValue(scrapData).addOnSuccessListener {
                    _isScrapped.value = true
                    onResult(true, null)
                }
            }
        }.addOnFailureListener {
            onResult(false, "네트워크 오류가 발생했습니다.")
        }
    }

    override fun onCleared() {
        super.onCleared()
        currentScrapRef?.removeEventListener(scrapListener!!)
    }


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