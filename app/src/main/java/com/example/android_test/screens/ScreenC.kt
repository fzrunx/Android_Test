package com.example.android_test.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@Composable
fun ScreenC(navController: NavController, modifier: Modifier = Modifier,viewModel: CountViewModel = viewModel()) {
    val viewModelCount = viewModel.counter.collectAsState()

    Surface(
        modifier = modifier.systemBarsPadding(),
        shape = RoundedCornerShape(20.dp),
        color = MaterialTheme.colorScheme.primary
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("뷰모델 이용 : ${viewModelCount.value}")
            Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                Button(
                    onClick = {viewModel.incrementCounter()},
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
                ) { Text(" 뷰 모델 증가") }
                Button(
                    onClick = {viewModel.decrementCounter()},
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
                ) { Text(" 뷰 모델 감소") }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true )
@Composable
fun CountPreview() {
    val navController = rememberNavController()
    ScreenC(navController = navController)
}
