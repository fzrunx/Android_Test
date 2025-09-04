package com.example.android_test.naver.books

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BooksRetrofitClient {
    private const val BASE_URL = "https://openapi.naver.com/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api: NaverBooksApi = retrofit.create(NaverBooksApi::class.java)
    val booksRetrofitRepository: BooksRetrofitRepository = BooksRetrofitRepository(api)
}