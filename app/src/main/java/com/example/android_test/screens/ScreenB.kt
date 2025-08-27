package com.example.android_test.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ScreenB(navController: NavController, modifier: Modifier = Modifier) {
    val count = remember { mutableStateOf(0) }
    var text by remember { mutableStateOf("") }

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        color = MaterialTheme.colorScheme.primary
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(20.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("B화면", fontSize = 24.sp)
            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("여기에 입력하세요") },
                modifier = Modifier.padding(30.dp).size(350.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = { navController.navigate("screenA/${text}") },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
            ) {
                Text("홈 화면으로 이동")
            }
            Text("카운터: ${count.value}")
            Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                Button(
                    onClick = { count.value++ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
                ) { Text(" +1 증가") }

                Button(
                    onClick = { count.value-- },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                ) { Text(" -1 감소") }
            }
        }
    }
}
