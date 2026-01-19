package com.example.knewz.ui.keyword

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.knewz.data.repository.KeywordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KeywordViewModel @Inject constructor(
    private val repository: KeywordRepository
) : ViewModel() {

    var keywordInput by mutableStateOf("")
        private set

    fun onKeywordInputChanged(newValue: String) {
        keywordInput = newValue
    }

    fun registerKeyword() {
        if (keywordInput.isBlank()) return

        viewModelScope.launch {
            val result = repository.addKeyword(keywordInput.trim())
            if (result.isSuccess) {
                keywordInput = ""
            }
        }
    }
}