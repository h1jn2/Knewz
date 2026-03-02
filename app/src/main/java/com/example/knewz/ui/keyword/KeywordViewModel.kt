package com.example.knewz.ui.keyword

import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.knewz.data.repository.KeywordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KeywordViewModel @Inject constructor(
    private val repository: KeywordRepository
) : ViewModel() {
    private val _toastEvent = Channel<String>()
    val toastEvent = _toastEvent.receiveAsFlow()
    var keywordList by mutableStateOf<List<KeywordItem>>(emptyList())
        private set

    var keywordInput by mutableStateOf("")
        private set

    init {
        loadKeywords()
    }

    private fun loadKeywords() {
        viewModelScope.launch {
            repository.getKeywords().collect { list ->
                keywordList = list.sortedByDescending { it.createdAt }
            }
        }
    }

    fun onKeywordInputChanged(newValue: String) {
        keywordInput = newValue
    }

    fun registerKeyword() {
        if (keywordInput.isBlank()) return
        viewModelScope.launch {
            val result = repository.addKeyword(keywordInput.trim())
            if (result.isSuccess) {
                keywordInput = ""
                _toastEvent.send("키워드가 등록되었습니다.")
            } else {
                _toastEvent.send("등록에 실패했습니다.")
            }
        }
    }

    fun deleteKeyword(item: KeywordItem) {
        viewModelScope.launch {
            val result = repository.deleteKeyword(item.id)
            if (result.isSuccess) {
                _toastEvent.send("'${item.keyword}' 키워드가 삭제되었습니다.")
            }
        }
    }

    fun toggleNotification(item: KeywordItem) {
        viewModelScope.launch {
            val nextState = !item.notifyEnabled
            val result = repository.updateNotifyEnabled(item.id, nextState)
            if (result.isSuccess) {
                val status = if (nextState) "ON" else "OFF"
                _toastEvent.send("'${item.keyword}' 알림이 ${status} 되었습니다.")
            }
        }
    }
}