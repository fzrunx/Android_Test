package com.example.android_test.news.retrofit




class NewsRepository {
    private val api = NewsInstance.api

    suspend fun searchNews(query: String): List<NewsRoom> {
        val response = api.getNews(query)
        return if (response.isSuccessful) {
            response.body()?.items?.map { it.toNewsRoom(query) } ?: emptyList()
        } else {
            println("API 호출 실패: ${response.code()} ${response.message()}")
            emptyList()
        }
    }
}
