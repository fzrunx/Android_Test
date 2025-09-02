package com.example.android_test.user.info

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.rememberNavController
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun UserList(navController: NavController, modifier: Modifier = Modifier, viewModel: UserViewModel = viewModel()) {
    val viewModelUser = viewModel.user_list.collectAsState()
    var searchQuery by remember { mutableStateOf("") }
    val selectedUsers = remember { mutableStateListOf<UserRoom>() }
    val users by viewModel.user_list.collectAsState()
    val filteredUsers = users.filter { user ->
        searchQuery.isBlank() ||
                user.userid.contains(searchQuery.toString(), ignoreCase = true) ||
                user.username.contains(searchQuery.toString(), ignoreCase = true) ||
                user.useremail.contains(searchQuery.toString(), ignoreCase = true)
    }
    Column(modifier = modifier
        .fillMaxSize()
        .systemBarsPadding(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Text(
            "사용자 리스트",
            fontSize = 20.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(20.dp)
        )
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("검색어를 입력해 주세요.") }
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight(0.9f) // 화면의 80% 사용
                .fillMaxWidth(1f)
                .padding(10.dp)
        ) {
            items(filteredUsers) { item ->
                val checkedItems by viewModel.checkedItems.collectAsState()
                val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                val registerDateStr = sdf.format(Date(item.registerDate))
                Card(modifier = modifier
                    .padding(8.dp)
                    .clickable {
                        val index = viewModelUser.value.indexOf(item)
                        navController.navigate("detail_User/$index")} )
                    {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                                checked = selectedUsers.contains(item),
                                onCheckedChange = {
                                    if (it) selectedUsers.add(item)
                                    else selectedUsers.remove(item)
                                },
                            modifier = Modifier
                                .padding(start = 3.dp)
                                .size(25.dp)
                        )
                        Text(
                            text = """
                                ID: ${item.userid} 
                                이름: ${item.username}
                                이메일: ${item.useremail}
                                전화번호: ${item.userphone}
                                주소: ${item.useraddress}
                                등록일: $registerDateStr
                                """.trimIndent(),
                            fontSize = 14.sp,
                            modifier = Modifier
                                .weight(1f) // ✅ 텍스트가 공간을 차지하도록
                                .padding(start = 15.dp), // ✅ 체크박스와 텍스트 사이 간격
                        )
                        IconButton(
                            onClick = { viewModel.deleteList(item) },
                            modifier = Modifier
                                .size(40.dp) // ✅ 버튼 크기 줄이기
                                .padding(start = 15.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Delete,
                                contentDescription = "삭제",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }

                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) { Button(
            onClick = {
                selectedUsers.forEach { user -> viewModel.deleteList(user) }
                selectedUsers.clear()
            },
            enabled = selectedUsers.isNotEmpty()
        ) {
            Text("선택 삭제")
        }

            Spacer(modifier = Modifier.width(10.dp))

            // 사용자 추가
            Button(onClick = { navController.navigate("user_Add") }) {
                Text("사용자 추가")
            }
        }
    }
}
