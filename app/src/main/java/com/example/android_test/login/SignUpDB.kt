package com.example.android_test.login

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [SignUpRoom::class], version = 1)
 abstract class SignUpDB: RoomDatabase() {
    abstract fun signUpDao(): SignUpDao

    companion object {
        @Volatile private var INSTANCE: SignUpDB? = null

        fun getInstance(context: Context): SignUpDB =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    SignUpDB::class.java,
                    "signUp_db"
                ).build().also {INSTANCE = it}
            }
    }
}
