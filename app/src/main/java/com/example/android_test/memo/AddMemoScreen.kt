package com.example.android_test.memo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.AlertDialog
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun AddMemoScreen(navController: NavController, modifier: Modifier = Modifier,viewModel: MemoViewModel = viewModel ()) {
    var title by remember { mutableStateOf("") }
    var text by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    Column(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "새 메모 작성",
            fontSize = 20.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(20.dp)
        )
        Column() {
            OutlinedTextField(
                value = title,
                onValueChange = { if (it.length <= 50) {
                    title = it }
                    },
                modifier = modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                placeholder = {Text("제목")}
            )
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                modifier = modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .size(400.dp),
                placeholder = {Text("내용")}
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Button(onClick = {
                if (title.isNotBlank() && text.isNotBlank()) { // 제목과 내용이 비어있지 않을 때만 추가
                    viewModel.addMemo(title, text)
                    navController.navigate("Memo_Home")
                }else {
                    showDialog = true
                }
            }) {
                Text("작성")
            }
        }
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                confirmButton = {
                    Button(onClick = { showDialog = false }) {
                        Text("확인")
                    }
                },
                title = { Text("입력 오류") },
                text = { Text("제목과 내용을 입력해주세요.") }
            )
        }
    }

}






@Preview(showBackground = true, showSystemUi = true )
@Composable
fun AddMemoPreview() {
    val navController = rememberNavController()
    AddMemoScreen(navController = navController)
}