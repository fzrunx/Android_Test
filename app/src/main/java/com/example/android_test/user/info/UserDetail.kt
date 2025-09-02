package com.example.android_test.user.info

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun UserDetail(navController: NavController, modifier: Modifier = Modifier, userIndex: Int, viewModel: UserViewModel = viewModel()) {
    val user = viewModel.user_list.value[userIndex]
    var userid = user.userid // ✅ ID는 수정 불가이므로 상태로 두지 않음
    var username = user.username // ✅ 이름은 수정 불가이므로 상태로 두지 않음
    var useremail by remember { mutableStateOf(user.useremail) }
    var userphone by remember { mutableStateOf(user.userphone) }
    var useraddress by remember { mutableStateOf(user.useraddress) }
    var showDialog by remember { mutableStateOf(false) }
    Column(modifier = modifier
        .fillMaxSize()
        .systemBarsPadding(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Text(
            "사용자 정보 수정",
            fontSize = 20.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(20.dp)
        )
        Column() {
            OutlinedTextField(
                value = userid,
                onValueChange = { /* 수정 불가 */ },
                enabled = false, // ✅ 수정 불가
                modifier = modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                placeholder = { Text("ID") }
            )
            OutlinedTextField(
                value = username,
                onValueChange = { /* 수정 불가 */ },
                enabled = false, // ✅ 수정 불가
                modifier = modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                placeholder = {Text("이름")}
            )
            OutlinedTextField(
                value = useremail,
                onValueChange = { useremail = it},
                modifier = modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                placeholder = {Text("email")}
            )
            OutlinedTextField(
                value = userphone,
                onValueChange = { userphone = it},
                modifier = modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                placeholder = {Text("전화번호")}
            )
            OutlinedTextField(
                value = useraddress,
                onValueChange = { useraddress = it},
                modifier = modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                placeholder = {Text("주소")}
            )
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            val registerDateStr = sdf.format(Date(user.registerDate))
            Text("등록일: $registerDateStr",
                fontSize = 20.sp,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(20.dp))
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Button(onClick = {
                viewModel.updateUser(userIndex, userid,username,useremail, userphone, useraddress )  // 기존 메모 수정
                navController.navigate("user_List")
            }) {
                Text("수정")
            }
        }
        if (showDialog) {
            androidx.compose.material3.AlertDialog(
                onDismissRequest = { showDialog = false },
                confirmButton = {
                    Button(onClick = { showDialog = false }) {
                        Text("확인")
                    }
                },
                title = { Text("입력 오류") },
                text = { Text("내용을 모두 입력해주세요.") }
            )
        }
    }
}