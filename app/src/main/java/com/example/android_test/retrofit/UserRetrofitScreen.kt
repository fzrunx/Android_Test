package com.example.android_test.retrofit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserRetrofitScreen(viewModel: UserRetrofitViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val users = viewModel.userList // ViewModel에서 가져오기
    // 화면 진입 시 한번만 실행
    LaunchedEffect(Unit) {
        viewModel.fetchUsers()
    }
    Scaffold(
        topBar = { CenterAlignedTopAppBar (title = { Text("사용자 목록", style = MaterialTheme.typography.titleLarge) } )}
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding)
        ){
            items(users) { user ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ){
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text(text = user.name, style = MaterialTheme.typography.titleMedium)
                        Text(text = user.email, style = MaterialTheme.typography.bodyMedium)
                        Text(text = user.address.toString(), style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }

    }
}