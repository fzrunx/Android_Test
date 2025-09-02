package com.example.android_test.user.info

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [UserRoom::class], version = 1)
abstract class UserDB: RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile private var INSTANCE: UserDB? = null

        fun getInstance(context: Context): UserDB =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    UserDB::class.java,
                    "user_db"
                ).build().also {INSTANCE = it}
            }
    }
}
