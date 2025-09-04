package com.example.android_test.naver.news

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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun NaverNewsScreen(navController: NavController, modifier: Modifier = Modifier, viewModel: NaverViewModel = viewModel()) {
    val naverNewsList by viewModel.naverNewsList.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState(initial = "")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .zIndex(1f),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            OutlinedTextField(
                value = searchQuery,
                onValueChange = {viewModel.updateSearchQuery(it)},
                modifier = Modifier.weight(1f),
                label = { Text("검색어 입력") },
                singleLine = true
            )
            Button(
                onClick = {
                    viewModel.naverFetchNews(searchQuery)
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
            items(naverNewsList) {navernews ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable{
                            val encodedUrl = URLEncoder.encode(navernews.link, StandardCharsets.UTF_8.toString())
                            navController.navigate("naverwebview/$encodedUrl")
                        },
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(Modifier.padding(8.dp)) {
                        Text(navernews.title, style = MaterialTheme.typography.titleMedium)
                        Spacer(Modifier.height(4.dp))
                        Text(navernews.description, style = MaterialTheme.typography.bodyMedium)
                        Spacer(Modifier.height(4.dp))
                        Text(navernews.pubDate, style = MaterialTheme.typography.labelSmall)
                    }
                }
            }

        }
    }
}