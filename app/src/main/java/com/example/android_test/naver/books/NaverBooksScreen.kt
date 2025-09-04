package com.example.android_test.naver.books

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun NaverBooksScreen(navController: NavController, viewModel: NaverBooksViewModel = viewModel()) {
    val booksList by viewModel.booksList.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
            ,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            TextField(
                value = searchQuery,
                onValueChange = { searchQuery = it},
                modifier = Modifier.weight(1f),
                label = { Text("검색어 입력") },
                singleLine = true
            )
            Button(
                onClick = {
                    Log.d("NAVER_BOOKS", "검색어: $searchQuery")
                    viewModel.naverFetchBooks(searchQuery)
                },
                modifier = Modifier.alignByBaseline()
            ){
                Text("검색")
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(booksList) {naverbooks ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable{
                            val encodedUrl = URLEncoder.encode(naverbooks.link, StandardCharsets.UTF_8.toString())
                            navController.navigate("naverbookswebview/$encodedUrl")
                        },
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(Modifier.padding(8.dp)) {
                        Text(naverbooks.title ?: "제목 없음", style = MaterialTheme.typography.titleMedium)
                        Spacer(Modifier.height(4.dp))
                        Text(naverbooks.description ?: "내용 없음", style = MaterialTheme.typography.bodyMedium)
                        Spacer(Modifier.height(4.dp))
                        Text(naverbooks.pubDate ?: "날짜 없음", style = MaterialTheme.typography.labelSmall)
                    }
                }
            }

        }
    }
}