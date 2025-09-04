package com.example.android_test.naver.books

class BooksRetrofitRepository(private val retrofitApi: NaverBooksApi) {
    suspend fun naverFetchBooks(query: String): NaverBooks =
        retrofitApi.naverSearchBooks(query)
}