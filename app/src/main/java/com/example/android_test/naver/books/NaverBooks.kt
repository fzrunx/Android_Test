package com.example.android_test.naver.books


data class NaverBooks(
    val lastBuildDate: String,
    val total: Int,
    val start: Int,
    val display: Int,
    val items: List<BooksItem>
)

data class BooksItem(
    val title: String?,
    val originallink: String?,
    val link: String?,
    val image: String?,
    val author: String?,
    val discount: Int?,
    val publisher: String?,
    val isbn: String?,
    val description: String?,
    val pubDate: String?
)