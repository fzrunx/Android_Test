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
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.android_test.screens.AddMemoScreen
import com.example.android_test.screens.DetailScreen
import com.example.android_test.screens.Memo
import com.example.android_test.screens.MemoViewModel
import com.example.android_test.screens.ScreenA
import com.example.android_test.screens.ScreenB
import com.example.android_test.screens.ScreenC



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val memoViewModel: MemoViewModel = androidx.lifecycle.viewmodel.compose.viewModel()

    // 현재 화면 route 가져오기
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    val showBars = currentRoute?.let {
        it != "add_memo_screen" && !it.startsWith("detail_Memo/")
    } ?: true

    Scaffold(
        topBar = { if (showBars) TopAppBar(title = { Text("상단") }) },
        bottomBar = { if (showBars) BottomAppBar { Text("하단") } }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "Memo_Home",
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .systemBarsPadding(),
        ) {
            composable("Memo_Home") { Memo(navController = navController, viewModel = memoViewModel) }
            composable("add_memo_screen") { AddMemoScreen(navController = navController, viewModel = memoViewModel) }
            composable("detail_Memo/{memoIndex}") { backStackEntry ->
                val memoIndex = backStackEntry.arguments?.getString("memoIndex")?.toInt() ?: 0
                DetailScreen(navController = navController, memoIndex = memoIndex, viewModel = memoViewModel)
            }
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