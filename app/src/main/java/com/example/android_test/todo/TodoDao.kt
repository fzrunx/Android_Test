package com.example.android_test.todo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo_table ORDER BY id DESC")
    fun getAllTodos(): Flow<List<TodoRoom>>

    @Insert
    suspend fun insert(todo: TodoRoom)

    @Update
    suspend fun update(todo: TodoRoom)

    @Delete
    suspend fun delete(todo: TodoRoom)

}