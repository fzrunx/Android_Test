package com.example.android_test.news.retrofit

import androidx.room.Entity
import androidx.room.PrimaryKey

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

@Entity(tableName = "navernews_table")  // 즉 여기 엔티티로 가져올때 위에 내가 필요한 Items에 데이터만 가져오게 작성하면됨
data class NewsRoom(
    @PrimaryKey val link: String,
    val title: String,
    val originallink: String,
    val description: String,
    val pubDate: String,
    val keyword: String,          // 검색어
)