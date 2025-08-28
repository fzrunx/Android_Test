package com.example.android_test.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {
    private val _counter = MutableLiveData(0)
    val counter: LiveData<Int> get() = _counter

    fun increase() {
        _counter.value = (_counter.value ?: 0) +1
    }
    fun decrease() {
        _counter.value = (_counter.value ?: 0) -1
    }
}