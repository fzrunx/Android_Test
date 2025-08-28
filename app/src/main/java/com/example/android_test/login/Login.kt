package com.example.android_test.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CheckboxDefaults.colors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController


@Composable
fun LoginScreen(navController: NavController, modifier: Modifier = Modifier) {
    var userId by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(25.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "로그인",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(25.dp))

        // 아이디 입력창
        TextField(
            value = userId,
            onValueChange = { userId= it },
            label = { Text("아이디") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        // 비밀번호 입력창
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("비밀번호") },
            visualTransformation = PasswordVisualTransformation(), // 입력 숨기기
            singleLine = true
        )

        Spacer(modifier = Modifier.height(25.dp))

        // 로그인 버튼
        Button(onClick = {
            println("아이디: $userId, 비밀번호: $password") },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
            Text(text = "로그인")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    val navController = rememberNavController()
    LoginScreen(navController = navController)
}