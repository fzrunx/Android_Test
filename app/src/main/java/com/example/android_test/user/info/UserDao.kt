package com.example.android_test.user.info

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM user WHERE userid = :userid LIMIT 1")
    suspend fun getUserByUserId(userid: String): UserRoom?

    @Query("SELECT * FROM user")
    fun getAllUsers(): Flow<List<UserRoom>>
    @Insert
    suspend fun insert(user: UserRoom)

    @Delete
    suspend fun deleteUser(user: UserRoom)
    @Delete
    suspend fun deleteUsers(users: List<UserRoom>)

    @Update
    suspend fun update(user: UserRoom)
}
