package com.example.android_test.user.info

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val userDao = UserDB.getInstance(application).userDao()
    private val _user_list = MutableStateFlow<List<UserRoom>>(emptyList())
    val user_list: StateFlow<List<UserRoom>> = _user_list
    private val _checkedItems = MutableStateFlow<Set<UserRoom>>(emptySet())
    val checkedItems: StateFlow<Set<UserRoom>> = _checkedItems

    init {
        viewModelScope.launch {
            userDao.getAllUsers().collect { users ->
                _user_list.value = users
            }
        }
    }

    fun addUser(user: UserRoom, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            // DB에 동일한 userid가 있는지 먼저 체크
            val exists = userDao.getUserByUserId(user.userid) != null
            if (exists) {
                onResult(false) // 이미 존재하는 ID
            } else {
                try {
                    userDao.insert(user)
                    onResult(true) // 성공적으로 추가됨
                } catch (e: Exception) {
                    onResult(false)
                }
            }
        }
    }
    fun updateUser(index: Int,userid: String, username: String, useremail: String, userphone: String, useraddress: String ) {
        viewModelScope.launch {
            val updatedList = _user_list.value.toMutableList()
            if (index in updatedList.indices) {
                val updatedUser = updatedList[index].copy(
                    useremail = useremail,
                    userphone = userphone,
                    useraddress = useraddress
                )
                updatedList[index] = updatedUser
                _user_list.value = updatedList

                // DB 업데이트
                userDao.update(updatedUser)
            }
        }
    }

    // 기존 삭제 (한 개)
    fun deleteList(user: UserRoom) {
        viewModelScope.launch {
            userDao.deleteUser(user)  // DB에서도 삭제
            _user_list.value = _user_list.value.filterNot { it == user }
            _checkedItems.value = _checkedItems.value - user
        }
    }
    // 다중 삭제
    fun deleteSelected() {
        viewModelScope.launch {
            val selected = _checkedItems.value.toList()
            userDao.deleteUsers(selected)  // DB에서 다중 삭제
            _user_list.value = _user_list.value.filterNot { selected.contains(it) }
            _checkedItems.value = emptySet()
        }
    }
    fun toggleCheck(user: UserRoom) {
        _checkedItems.value = if (_checkedItems.value.contains(user)) {
            _checkedItems.value - user
        } else {
            _checkedItems.value + user
        }
    }
}


