package com.example.android_test.news.retrofit

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NewsViewModel: ViewModel() {
    private val repository = NewsRepository()
    var newsList = mutableStateListOf<Items>()
        private set
    fun fetchNews() {
        viewModelScope.launch {
            try {
                val news = repository.searchNews( "코틀린")
                newsList.clear()
                newsList.addAll(news)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}