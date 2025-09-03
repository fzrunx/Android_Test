package com.example.android_test.news.retrofit

import com.example.android_test.retrofit.RetrofitInstance


class NewsRepository {
    private val api = NewsInstance.api

    suspend fun searchNews(query: String): List<Items> { // Items 단위로 반환
        val response = api.getNews(query)
        if (response.isSuccessful) {
            return response.body()?.items ?: emptyList()
        } else {
            println("API 호출 실패: ${response.code()} ${response.message()}")
            return emptyList()
        }
    }
}