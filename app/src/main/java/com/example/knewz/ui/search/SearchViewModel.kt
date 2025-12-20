package com.example.knewz.ui.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.knewz.domain.usecase.GetNewsUseCase
import com.example.knewz.domain.usecase.ManageSearchHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val manageSearchHistoryUseCase: ManageSearchHistoryUseCase
): ViewModel() {
    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText.asStateFlow()
    val searchHistory: StateFlow<List<String>> = manageSearchHistoryUseCase.getHistory()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

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

    fun deleteSearchHistory(query: String) {
        viewModelScope.launch {
            manageSearchHistoryUseCase.deleteQuery(query)
        }
    }
}