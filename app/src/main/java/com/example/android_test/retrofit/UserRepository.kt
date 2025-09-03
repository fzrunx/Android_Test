package com.example.android_test.retrofit

class UserRepository {
    private val api = RetrofitInstance.api

    suspend fun getUsers(): List<User> {
        return RetrofitInstance.api.getUsers()
    }
}