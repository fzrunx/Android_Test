package com.example.android_test.news.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NewsInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://openapi.naver.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: NewsApiService by lazy {
        retrofit.create(NewsApiService::class.java)
    }
}