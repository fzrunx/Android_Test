package com.example.android_test.retrofit

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserRetrofitViewModel: ViewModel() {
    private val repository = UserRepository()
    var userList = mutableStateListOf<User>()
        private set
    fun fetchUsers() {
        viewModelScope.launch {
            try {
                val users = repository.getUsers()
                userList.clear()
                userList.addAll(users)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}