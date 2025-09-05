package com.example.android_test.news.retrofit

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NewsRoom::class], version = 1)
abstract class NewsDB: RoomDatabase() {
    abstract fun newsDao() : NewsDao

    companion object {
        @Volatile private var INSTANCE: NewsDB? = null

        fun getInstance(context: Context): NewsDB =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    NewsDB::class.java,
                    "news_db"
                ).build().also { INSTANCE = it }
            }
    }
}
