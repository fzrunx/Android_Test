package com.example.android_test.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val signUpDao = SignUpDB.getInstance(application).signUpDao()
    private val _signup = MutableStateFlow<List<SignUpRoom>>(emptyList())
    val signup: StateFlow<List<SignUpRoom>> = _signup
    private val _checkedItems = MutableStateFlow<Set<SignUpRoom>>(emptySet())
    val checkedItems: StateFlow<Set<SignUpRoom>> = _checkedItems

        init {
            viewModelScope.launch {
                signUpDao.getAllUsers().collect { users ->
                    _signup.value = users
                }
            }
        }

        fun addUser(user: SignUpRoom, onResult: (Boolean) -> Unit) {
            viewModelScope.launch {
                // DB에 동일한 userid가 있는지 먼저 체크
                val exists = signUpDao.getUserByUserId(user.userid) != null
                if (exists) {
                    onResult(false) // 이미 존재하는 ID
                } else {
                    try {
                        signUpDao.insert(user)
                        onResult(true) // 성공적으로 추가됨
                    } catch (e: Exception) {
                        onResult(false)
                    }
                }
            }
        }
        fun updateUser(index: Int,userid: String, username: String, useremail: String, userphone: String, useraddress: String ) {
            viewModelScope.launch {
                val updatedList = _signup.value.toMutableList()
                if (index in updatedList.indices) {
                    val updatedUser = updatedList[index].copy(
                        useremail = useremail,
                        userphone = userphone,
                        useraddress = useraddress
                    )
                    updatedList[index] = updatedUser
                    _signup.value = updatedList

                    // DB 업데이트
                    signUpDao.update(updatedUser)
                }
            }
        }

        // 기존 삭제 (한 개)
        fun deleteList(user: SignUpRoom) {
            viewModelScope.launch {
                signUpDao.deleteUser(user)  // DB에서도 삭제
                _signup.value = _signup.value.filterNot { it == user }
                _checkedItems.value = _checkedItems.value - user
            }
        }
        // 다중 삭제
        fun deleteSelected() {
            viewModelScope.launch {
                val selected = _checkedItems.value.toList()
                signUpDao.deleteUsers(selected)  // DB에서 다중 삭제
                _signup.value = _signup.value.filterNot { selected.contains(it) }
                _checkedItems.value = emptySet()
            }
        }
        fun toggleCheck(user: SignUpRoom) {
            _checkedItems.value = if (_checkedItems.value.contains(user)) {
                _checkedItems.value - user
            } else {
                _checkedItems.value + user
            }
        }
    }
