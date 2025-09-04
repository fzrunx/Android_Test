package com.example.android_test.naver.books

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NaverBooksViewModel: ViewModel() {
    private val _booksList = MutableStateFlow<List<BooksItem>>(emptyList())
    val booksList: StateFlow<List<BooksItem>> = _booksList

    private val repository: BooksRetrofitRepository = BooksRetrofitClient.booksRetrofitRepository

    fun naverFetchBooks(query: String) {
        viewModelScope.launch {
            try{
                val response = repository.naverFetchBooks(query= query)
                _booksList.value = response.items
            }catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}