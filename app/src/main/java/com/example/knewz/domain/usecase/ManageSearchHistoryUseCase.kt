package com.example.knewz.domain.usecase

import com.example.knewz.data.local.SearchHistoryDao
import com.example.knewz.data.local.SearchHistoryEntity
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ManageSearchHistoryUseCase @Inject constructor(
    private val dao: SearchHistoryDao
) {
    fun getHistory() = dao.getRecentSearchHistory().map { list ->
        list.map { it.query }
    }

    suspend fun saveQuery(query: String) {
        if (query.isBlank()) return
        dao.insertSearch(SearchHistoryEntity(query = query))
    }

    suspend fun deleteQuery(query: String) {
        dao.deleteSearch(query)
    }
}