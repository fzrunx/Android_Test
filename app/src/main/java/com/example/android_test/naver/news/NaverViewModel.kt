package com.example.android_test.naver.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NaverViewModel: ViewModel() {
    private val _naverNewList = MutableStateFlow<List<NewsItem>>(emptyList())
    val naverNewsList: StateFlow<List<NewsItem>> = _naverNewList

    private val repository: NaverRepository = RetrofitClient.naverRepository

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow() // read-only

    fun updateSearchQuery(newQuery: String) {
        _searchQuery.value = newQuery
    }


    fun naverFetchNews(query: String) {
        viewModelScope.launch {
            try {
                val response = repository.naverFetchNews(query= query)
                _naverNewList.value = response.items
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    }
