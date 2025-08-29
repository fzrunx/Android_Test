package com.example.android_test.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch



data class Memo(
    val title: String,
    val content: String
)
class MemoViewModel: ViewModel() {
    private val _memo = MutableStateFlow<List<Memo>>(emptyList())
    val memo: StateFlow<List<Memo>> = _memo
    private val _checkedItems = MutableStateFlow<Set<Memo>>(emptySet())
    val checkedItems: StateFlow<Set<Memo>> = _checkedItems

    fun addMemo(title: String, content: String) {
        viewModelScope.launch {
            _memo.update { currentList ->
                currentList + Memo(title = title, content = content)
            }
        }
    }
    fun deleteMemo(deleteMemo: Memo) {
        viewModelScope.launch {
            _memo.update { currentList ->
                currentList - deleteMemo
            }
        }
    }
    fun updateMemo(index: Int, title: String, content: String) {
        _memo.value = _memo.value.toMutableList().also {
            if (index in it.indices) {
                it[index] = Memo(title, content)
            }
        }
    }
}