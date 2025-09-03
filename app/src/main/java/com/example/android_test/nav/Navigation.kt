package com.example.android_test.nav


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.android_test.login.LoginScreen
import com.example.android_test.login.LoginViewModel
import com.example.android_test.login.SignUp
import com.example.android_test.memo.AddMemoScreen
import com.example.android_test.memo.DetailScreen
import com.example.android_test.memo.Memo
import com.example.android_test.memo.MemoViewModel
import com.example.android_test.news.retrofit.NewsScreen
import com.example.android_test.news.retrofit.NewsViewModel
import com.example.android_test.news.retrofit.NewsWebView
import com.example.android_test.retrofit.UserRetrofitScreen
import com.example.android_test.retrofit.UserRetrofitViewModel
import com.example.android_test.screens.ScreenA
import com.example.android_test.screens.ScreenB
import com.example.android_test.screens.ScreenC
import com.example.android_test.todo.ToDo
import com.example.android_test.todo.ToDoViewModel
import com.example.android_test.user.info.UserAdd
import com.example.android_test.user.info.UserDetail
import com.example.android_test.user.info.UserList
import com.example.android_test.user.info.UserViewModel




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val memoViewModel: MemoViewModel = viewModel()
    val todoViewModel: ToDoViewModel = viewModel()
    val userViewModel: UserViewModel = viewModel()
    val loginViewModel: LoginViewModel = viewModel()
    val userRetrofitViewModel: UserRetrofitViewModel = viewModel()
    val newsViewModel: NewsViewModel = viewModel()

    // 현재 화면 route 가져오기
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    val showBars = currentRoute?.let {
        it != "add_memo_screen" && !it.startsWith("detail_Memo/")
    } ?: true

    Scaffold(
        topBar = { if (showBars) TopBar(navController) },
        bottomBar = { if (showBars) BottomAppBar(navController) }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "screenA",
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .systemBarsPadding(),
        ) {
            composable("webview/{url}") { backStackEntry ->
                val url = backStackEntry.arguments?.getString("url") ?: ""
                NewsWebView(url = url)
            }
            composable ("news"){ NewsScreen(navController = navController, viewModel = newsViewModel)  }
            composable ("retrofit"){  UserRetrofitScreen(viewModel = userRetrofitViewModel) }
            composable ("sign_Up"){ SignUp(navController = navController, viewModel = loginViewModel) }
            composable ("login") { LoginScreen(navController = navController, viewModel = loginViewModel) }
            composable ("user_List") { UserList(navController = navController, viewModel = userViewModel) }
            composable ("user_Add") { UserAdd(navController = navController, viewModel = userViewModel) }
            composable("detail_User/{userIndex}") { backStackEntry ->
                val userIndex = backStackEntry.arguments?.getString("userIndex")?.toInt() ?: 0
                UserDetail(navController = navController, userIndex = userIndex, viewModel = userViewModel)
            }
            composable("Todo") { ToDo(navController = navController, viewModel = todoViewModel) }
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