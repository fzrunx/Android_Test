package com.example.android_test.todo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch





class ToDoViewModel(application: Application): AndroidViewModel(application) {
    private val todoDao = TodoDB.getInstance(application).todoDao()
    private val _todo_list = MutableStateFlow<List<TodoRoom>>(emptyList())
    val todo_list: StateFlow<List<TodoRoom>> = _todo_list
    private val _checkedItems = MutableStateFlow<Set<TodoRoom>>(emptySet())
    val checkedItems: StateFlow<Set<TodoRoom>> = _checkedItems

    init {
        viewModelScope.launch {
            todoDao.getAllTodos().collect { todos ->
                _todo_list.value = todos
            }
        }
    }

    fun addList(todo: TodoRoom) {
        viewModelScope.launch {
            todoDao.insert(todo)
        }
    }
    fun deleteList(todo: TodoRoom) {
        viewModelScope.launch {
            todoDao.delete(todo)
        }
    }
    fun toggleCheck(todo: TodoRoom) {
        viewModelScope.launch {
            todoDao.update(todo.copy(isDone = !todo.isDone))
        }
    }
}