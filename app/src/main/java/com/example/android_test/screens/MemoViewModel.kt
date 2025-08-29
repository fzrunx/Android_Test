package com.example.android_test.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MemoViewModel: ViewModel() {
    private val _memo_list = MutableStateFlow<List<String>>(emptyList())
    val memo_list: StateFlow<List<String>> = _memo_list
    private val _checkedItems = MutableStateFlow<Set<String>>(emptySet())
    val checkedItems: StateFlow<Set<String>> = _checkedItems

    fun addMemoList(newMemo: String) {
        viewModelScope.launch {
            _memo_list.update { currentList ->
                currentList + newMemo
            }
        }
    }

    fun deleteMemoList(deleteMemo: String) {
        viewModelScope.launch {
            _memo_list.update { currentList ->
                currentList - deleteMemo
            }

        }
    }
    fun toggleCheck(item: String) {
        _checkedItems.update { currentSet ->
            if (item in currentSet) {
                currentSet - item
            } else {
                currentSet + item
            }
        }
    }
}