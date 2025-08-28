package com.example.android_test.screens


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CountViewModel: ViewModel() {
    private val _counter = MutableStateFlow(0)
    val counter: StateFlow<Int> = _counter.asStateFlow()

    fun incrementCounter() {
        viewModelScope.launch {
            _counter.value = _counter.value + 1
        }
    }

    fun decrementCounter() {
        viewModelScope.launch {
            _counter.value = _counter.value -1
        }
    }
}

