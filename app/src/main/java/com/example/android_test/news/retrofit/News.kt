package com.example.android_test.news.retrofit

data class News (
    val total: Int,
    val start: Int,
    val display: Int,
    val items: List<Items>
)

data class Items(
    val title: String,
    val link: String,
    val originallink: String,
    val description: String,
    val pubDate: String
)