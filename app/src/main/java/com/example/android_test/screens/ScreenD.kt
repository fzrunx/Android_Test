package com.example.android_test.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun ScreenD(navController: NavController, modifier: Modifier = Modifier,viewModel: MyViewModel = viewModel()) {
    val count by viewModel.counter.observeAsState(0)

    Surface(
        modifier = modifier.systemBarsPadding(), // ✅ 시스템바 영역까지 패딩 적용
        shape = RoundedCornerShape(20.dp),
        color = MaterialTheme.colorScheme.secondary
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("라이브데이터 이용 : $count")
            Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                Button(
                    onClick = {viewModel.increase()},
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
                ) { Text(" 라이브 데이터 증가") }
                Button(
                    onClick = {viewModel.decrease()},
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
                ) { Text(" 라이브 데이터 감소") }
            }
        }
    }
}




@SuppressLint("ViewModelConstructorInComposable")
@Preview(
    showBackground = true,   // ✅ 배경 표시
    showSystemUi = true      // ✅ 상태바 / 내비게이션바 표시
)
@Composable
fun ScreenDPreview() {
    val navController = rememberNavController()
    ScreenD(navController = navController)
}