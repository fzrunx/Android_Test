package com.example.android_test.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@Composable
fun DetailScreen(navController: NavController,modifier: Modifier = Modifier,memoIndex: Int, viewModel: MemoViewModel = viewModel()) {
    val memo = viewModel.memo.value[memoIndex]
    var title by remember { mutableStateOf(memo.title) }
    var text by remember { mutableStateOf(memo.content) }
    Column(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "메모 수정",
            fontSize = 20.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(20.dp)
        )
        Column() {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
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
                viewModel.updateMemo(memoIndex, title, text)  // 기존 메모 수정
                navController.navigate("Memo_Home")
            }) {
                Text("수정")
            }
        }

    }
}





@Preview(showBackground = true, showSystemUi = true )
@Composable
fun DetailMemoPreview() {
    val navController = rememberNavController()
    val dummyIndex = 0  // 임시로 0번째 메모를 사용
    DetailScreen(navController = navController, memoIndex = dummyIndex)
}
