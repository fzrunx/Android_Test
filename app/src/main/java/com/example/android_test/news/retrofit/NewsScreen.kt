package com.example.android_test.news.retrofit

import android.net.Uri
import androidx.compose.foundation.clickable
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
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(navController: NavController, viewModel: NewsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val news = viewModel.newsList // ViewModel에서 가져오기
    // 화면 진입 시 한번만 실행
    LaunchedEffect(Unit) {
        viewModel.fetchNews()
    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(
                    "네이버 뉴스",
                    style = MaterialTheme.typography.titleLarge
                )
            })
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding)
        ) {
            items(news) { item->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable{
                                val encodedUrl = Uri.encode(item.link) // 특수문자 처리
                                navController.navigate("webview/$encodedUrl")
                            }
                    ) {
                        Column(modifier = Modifier.padding(8.dp)) {
                            Text(text = item.title, style = MaterialTheme.typography.bodyMedium)
                            Text(text = item.description, style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }

        }
    }
