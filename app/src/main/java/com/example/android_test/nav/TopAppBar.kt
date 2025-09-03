package com.example.android_test.nav

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavController) {
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    // 홈 화면(네비게이션 시작 화면)에서는 뒤로가기 버튼 숨김
    val showBackButton = currentRoute != "screenA" && currentRoute != "login"

    TopAppBar(
        title = { Text("상단바") },
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "뒤로가기")
                }
            }
        },
            actions = {
        // 오른쪽 버튼 추가
            IconButton(onClick = {navController.navigate("retrofit")
            }, modifier = Modifier
                .size(50.dp)
                .background(color = Color.Gray)) {
                Text("retrofit") // 텍스트 버튼 형태
            }
                IconButton(onClick = {navController.navigate("news")
                }, modifier = Modifier
                    .size(60.dp)
                    .padding(10.dp)
                    .background(color = Color.Gray)) {
                    Text("news") // 텍스트 버튼 형태
                }
        }
    )
}