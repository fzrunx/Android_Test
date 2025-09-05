package com.example.android_test.news.retrofit

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(news: List<NewsRoom>)

    @Query("SELECT * FROM navernews_table WHERE keyword = :keyword ORDER BY keyword ASC, pubDate DESC")
    fun getNewsByKeyword(keyword: String): Flow<List<NewsRoom>>

    @Query("SELECT * FROM navernews_table ORDER BY pubDate DESC")
    fun getAllNews(): Flow<List<NewsRoom>>

    @Delete
    suspend fun deleteNews(news: NewsRoom)
}