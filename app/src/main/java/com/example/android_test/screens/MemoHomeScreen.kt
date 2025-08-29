package com.example.android_test.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration


@Composable
fun Memo(navController: NavHostController,modifier: Modifier = Modifier, viewModel: MemoViewModel = viewModel()) {
    var text by remember { mutableStateOf("") }
    val viewModelMemo = viewModel.memo_list.collectAsState()
    Box(modifier = Modifier.fillMaxSize()) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("메모장",
            fontSize = 20.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(20.dp))
        if (viewModelMemo.value.isEmpty()) {  // 리스트가 비어있으면 안내 문구
                Text(
                    text = "메모 내용이 없습니다. 지금 추가하세요!",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp),
                    textAlign = TextAlign.Center
                )
            } else {
                // ✅ 리스트 영역: 스크롤 가능
                LazyColumn(
                    modifier = Modifier
                        .fillMaxHeight(0.9f) // 화면의 80% 사용
                        .fillMaxWidth(1f)
                        .padding(10.dp)
                ) {
                    items(viewModelMemo.value) { item ->
                        val checkedItems by viewModel.checkedItems.collectAsState()
                        val isChecked = checkedItems.contains(item)

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = viewModel.checkedItems.collectAsState().value.contains(item),
                                onCheckedChange = { viewModel.toggleCheck(item) },
                                modifier = Modifier
                                    .padding(start = 3.dp)
                                    .size(25.dp)
                            )
                            Text(
                                item,
                                fontSize = 14.sp,
                                modifier = Modifier
                                    .weight(1f) // ✅ 텍스트가 공간을 차지하도록
                                    .padding(start = 15.dp), // ✅ 체크박스와 텍스트 사이 간격
                                textDecoration = if (isChecked) TextDecoration.LineThrough else null // ✅ 체크 시 취소선
                            )
                            IconButton(
                                onClick = { viewModel.deleteMemoList(item) },
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
                        // ✅ 아이템 구분선
                        Divider(
                            color = Color.Gray,
                            thickness = 1.dp,
                            modifier = Modifier.padding(vertical = 10.dp)
                        )
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Button(onClick = {navController.navigate("add_memo_screen") })
            {
                Text("+")
            }
        }

    }
}




@Preview(showBackground = true, showSystemUi = true )
@Composable
fun MemoPreview() {
    val navController = rememberNavController()
    Memo(navController = navController)
}