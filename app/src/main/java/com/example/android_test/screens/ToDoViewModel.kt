package com.example.android_test.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ToDoViewModel: ViewModel() {
    private val _todo_list = MutableStateFlow<List<String>>(emptyList())
    val todo_list: StateFlow<List<String>> = _todo_list


    fun addList(newToDo: String) {
        viewModelScope.launch {
            _todo_list.update { currentList ->
                currentList + newToDo
            }
        }
    }
    fun deleteList(deleteToDo: String) {
        viewModelScope.launch {
            _todo_list.update { currentList ->
                currentList - deleteToDo
            }

        }
    }


}