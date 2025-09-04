package com.example.android_test.naver.books

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NaverBooksApi {
    @Headers(
        "X-Naver-Client-Id:1i1G59fuOO9BybJdY8dL",
        "X-Naver-Client-Secret:nP1aEldLVz"
    )
    @GET("v1/search/book.json")
    suspend fun naverSearchBooks(
        @Query("query") query: String,
        @Query("display") display: Int = 10,
        @Query("start") start: Int = 1,
        @Query("sort") sort: String ="sim"
    ): NaverBooks
}