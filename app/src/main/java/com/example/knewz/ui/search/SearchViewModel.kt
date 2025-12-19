package com.example.knewz.ui.search

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.knewz.domain.usecase.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchNewsUseCase: GetNewsUseCase
): ViewModel() {
    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText.asStateFlow()

    fun onSearchTextChanged(text: String) {
        _searchText.value = text
    }

    fun executeSearch(query: String) {
        Log.d("hjn", "검색 실행: $query")
    }
}