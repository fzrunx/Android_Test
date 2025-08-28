package com.example.android_test.nav

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.android_test.screens.ScreenA
import com.example.android_test.screens.ScreenB
import com.example.android_test.screens.ScreenC

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    Scaffold(
        topBar = { TopAppBar(title = { Text("상단") }) },
        bottomBar = { BottomAppBar { Text("하단") } }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "screenA",
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .systemBarsPadding(),
        ) {
            composable("screenA") { ScreenA(navController = navController, userName = "") }
            composable("screenB") { ScreenB(navController = navController) }
            composable("screenC") { ScreenC(navController = navController) }
            composable("screenA/{userName}") { backStackEntry ->
                val userName = backStackEntry.arguments?.getString("userName") ?: ""
                ScreenA(navController = navController, userName = userName)
            }
        }
    }
}