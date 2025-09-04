package com.example.android_test.naver.news

class NaverRepository(private val retrofitApi: NaverNewsApi) {
    suspend fun naverFetchNews(query: String): NaverNews =
        retrofitApi.naverSearchNews(query)
}