package com.example.android_test.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.lazy.items
@Composable
fun ToDo (navController: NavController,modifier: Modifier = Modifier, viewModel: ToDoViewModel = viewModel()) {
    var text by remember { mutableStateOf("") }
    val viewModelTodo = viewModel.todo_list.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("오늘 할 일 (MVVM)",
            fontSize = 20.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(20.dp))
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            modifier = modifier
                .padding(10.dp)
                .fillMaxWidth(),
            placeholder = {Text("새로운 할 일을 추가하세요")}
        )

        // ✅ 리스트 영역: 스크롤 가능
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight(0.9f) // 화면의 80% 사용
                .padding(10.dp)
        ) {
            items(viewModelTodo.value) { item ->
                Text(item)
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(onClick = {
                viewModel.addList(text)
                text = ""
            }) {
                Text("+")
            }
        }

    }
}


@Preview(showBackground = true, showSystemUi = true )
@Composable
fun ToDoPreview() {
    val navController = rememberNavController()
    ToDo(navController = navController)
}