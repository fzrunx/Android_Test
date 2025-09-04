package com.example.android_test.naver.news

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://openapi.naver.com/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api: NaverNewsApi = retrofit.create(NaverNewsApi::class.java)
    val naverRepository: NaverRepository = NaverRepository(api)
}